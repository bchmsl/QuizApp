package com.space.quiz_impl.presentation.quiz.fragment

import androidx.activity.addCallback
import com.space.common.base.fragment.QuizBaseFragment
import com.space.common.extensions.coroutines.observeLiveData
import com.space.common.extensions.coroutines.observeLiveDataNonNull
import com.space.common.extensions.utils.enable
import com.space.common.extensions.utils.withBinding
import com.space.common.util.QuizConstants.EMPTY_STRING
import com.space.quiz_impl.domain.usecase.FinishAlertUseCase
import com.space.quiz_impl.presentation.quiz.adapter.AnswersAdapter
import com.space.quiz_impl.presentation.quiz.model.QuizQuestionUiModel
import com.space.quiz_impl.presentation.quiz.viewmodel.QuizQuestionViewModel
import com.space.quizimpl.databinding.QuizFragmentQuestionBinding
import kotlin.reflect.KClass

class QuizQuestionFragment :
    QuizBaseFragment<QuizFragmentQuestionBinding, QuizQuestionViewModel>() {

    private val answersAdapter by lazy { AnswersAdapter() }

    private lateinit var subject: String
    private var points = 0

    override val vmc: KClass<QuizQuestionViewModel>
        get() = QuizQuestionViewModel::class

    override fun inflate(): com.space.common.util.Inflater<QuizFragmentQuestionBinding> =
        QuizFragmentQuestionBinding::inflate

    override fun onFragmentCreate() {
//        subject = arguments?.getString(TAG_STRING).toString()
        vm.getQuestionCount(subject)
    }

    override fun onBind() {
        super.onBind()
        withBinding {
            navigationView.setContent(
                subject,
                closeAvailable = true,
                backAvailable = false,
                starAvailable = false
            )
            AnswerOptionsRecyclerView.adapter = answersAdapter
            nextButton.text = getString(com.space.common.util.S.next)
            nextButton.enable(false)
        }
    }

    override fun observe() {
        observeLiveDataNonNull(vm.questionState) { question ->
            onQuestionLoaded(question)
        }

        observeLiveDataNonNull(vm.answersListState) { checkedList ->
            with(answersAdapter) {
                submitList(checkedList.toList())
                notifyItemRangeChanged(0, itemCount)
                setNextButtonClickListener()
            }
        }

        observeLiveData(vm.pointsState) { points ->
            this.points = points
            binding.progressView.setPoints(this.points)
        }

        observeLiveDataNonNull(vm.finishAlertState) { finishAlertResponse ->
            showCongratsAlert(finishAlertResponse, this.points)
        }

        observeLiveData(vm.questionCount) { questionsCount ->
            binding.progressView.setMaxValue(questionsCount)
        }
    }

    private fun setNextButtonClickListener() {
        binding.nextButton.setOnClickListener {
            vm.getNextQuestion(subject)
        }
    }

    private fun onQuestionLoaded(question: QuizQuestionUiModel) {
        with(binding) {
            questionTextView.text = question.questionTitle
            answersAdapter.submitList(question.answers.toList())
            answersAdapter.point = { question.points }
            nextButton.text =
                getString(if (question.isLastQuestion) com.space.common.util.S.finish else com.space.common.util.S.next)
            nextButton.setOnClickListener(null)
            nextButton.enable(false)
            progressView.setProgress(question.questionIndex + 1)
        }
        setAdapterItemClickListener()
    }

    private fun setAdapterItemClickListener() {
        answersAdapter.onItemClickListener { answer ->
            vm.checkAnswer(answer, subject)
            answersAdapter.onItemClickListener(null)
            binding.nextButton.enable(true)
        }
    }

    override fun setListeners() {
        binding.navigationView.onCloseButtonPressed {
            showQuitDialog()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            showQuitDialog()
        }
    }

    private fun showQuitDialog() {
        showPromptDialog(com.space.common.util.S.stop_quiz_prompt, onPositiveButton = {
            vm.navigateToHome()
        })
    }

    private fun showCongratsAlert(response: FinishAlertUseCase.FinishAlertResponse, score: Int) {
        val dialog = alertDialog
        dialog.setMessage(if (response.isCongratsVisible) getString(com.space.common.util.S.congrats) else EMPTY_STRING)
        dialog.setTitle(response.emoji)
        (dialog
            .setDescription(
                String.format(
                    getString(com.space.common.util.S.you_earned_points),
                    score
                )
            )
            .setButton(getString(com.space.common.util.S.close)) {
                vm.navigateToHome()
                it.dismiss()
                binding.progressView.clear()
            }.build() as com.space.common.dialog.QuizAlertDialogView).show()

    }
}
