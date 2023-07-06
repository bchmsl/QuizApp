package com.space.quizapp.presentation.ui.ui_question.fragment

import androidx.activity.addCallback
import com.space.quizapp.common.extensions.coroutines.observeLiveData
import com.space.quizapp.common.extensions.coroutines.observeLiveDataNonNull
import com.space.quizapp.common.extensions.utils.enable
import com.space.quizapp.common.extensions.utils.withBinding
import com.space.quizapp.common.util.Inflater
import com.space.quizapp.common.util.QuizConstants.EMPTY_STRING
import com.space.quizapp.common.util.S
import com.space.quizapp.databinding.QuizFragmentQuestionBinding
import com.space.quizapp.domain.usecase.questions.FinishAlertUseCase
import com.space.quizapp.presentation.base.fragment.QuizBaseFragment
import com.space.quizapp.presentation.model.quiz.QuizQuestionUiModel
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections.Companion.TAG_STRING
import com.space.quizapp.presentation.ui.common.view.dialog.QuizAlertDialogView
import com.space.quizapp.presentation.ui.ui_question.adapter.AnswersAdapter
import com.space.quizapp.presentation.ui.ui_question.vm.QuizQuestionViewModel
import kotlin.reflect.KClass

class QuizQuestionFragment :
    QuizBaseFragment<QuizFragmentQuestionBinding, QuizQuestionViewModel>() {

    private val answersAdapter by lazy { AnswersAdapter() }

    private lateinit var subject: String
    private var points = 0

    override val vmc: KClass<QuizQuestionViewModel>
        get() = QuizQuestionViewModel::class

    override fun inflate(): Inflater<QuizFragmentQuestionBinding> =
        QuizFragmentQuestionBinding::inflate

    override fun onFragmentCreate() {
        subject = arguments?.getString(TAG_STRING).toString()
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
            nextButton.text = getString(S.next)
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
            nextButton.text = getString(if (question.isLastQuestion) S.finish else S.next)
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
        showPromptDialog(S.stop_quiz_prompt, onPositiveButton = {
            vm.navigateToHome()
        })
    }

    private fun showCongratsAlert(response: FinishAlertUseCase.FinishAlertResponse, score: Int) {
        val dialog = alertDialog
        dialog.setMessage(if (response.isCongratsVisible) getString(S.congrats) else EMPTY_STRING)
        dialog.setTitle(response.emoji)
        (dialog
            .setDescription(String.format(getString(S.you_earned_points), score))
            .setButton(getString(S.close)) {
                vm.navigateToHome()
                it.dismiss()
                binding.progressView.clear()
            }.build() as QuizAlertDialogView).show()

    }
}
