package com.space.quizapp.presentation.ui.ui_question.fragment

import androidx.activity.addCallback
import com.space.quizapp.common.extensions.coroutines.observeLiveData
import com.space.quizapp.common.extensions.coroutines.observeLiveDataNonNull
import com.space.quizapp.common.extensions.utils.withBinding
import com.space.quizapp.common.util.Inflater
import com.space.quizapp.common.util.S
import com.space.quizapp.databinding.QuizFragmentQuestionBinding
import com.space.quizapp.presentation.base.fragment.QuizBaseFragment
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections.Companion.TAG_INT
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections.Companion.TAG_STRING
import com.space.quizapp.presentation.ui.common.view.dialog.QuizAlertDialogView
import com.space.quizapp.presentation.ui.common.view.dialog.QuizDialogFactory
import com.space.quizapp.presentation.ui.common.view.dialog.QuizPromptDialogView
import com.space.quizapp.presentation.ui.ui_question.adapter.AnswersAdapter
import com.space.quizapp.presentation.ui.ui_question.vm.QuizQuestionViewModel
import kotlin.reflect.KClass

class QuizQuestionFragment :
    QuizBaseFragment<QuizFragmentQuestionBinding, QuizQuestionViewModel>() {

    private val answersAdapter by lazy { AnswersAdapter() }
    private val promptDialog by lazy {
        QuizDialogFactory.createDialog(
            QuizDialogFactory.Dialog.DIALOG_PROMPT,
            requireContext()
        ) as QuizPromptDialogView.Builder
    }
    private val alertDialog by lazy {
        QuizDialogFactory.createDialog(
            QuizDialogFactory.Dialog.DIALOG_ALERT,
            requireContext()
        ) as QuizAlertDialogView.Builder
    }

    private lateinit var subject: String
    private var subjectId: Int = -1

    private var points = 0

    override val vmc: KClass<QuizQuestionViewModel>
        get() = QuizQuestionViewModel::class

    override fun inflate(): Inflater<QuizFragmentQuestionBinding> =
        QuizFragmentQuestionBinding::inflate

    override fun onFragmentCreate() {
        subject = arguments?.getString(TAG_STRING).toString()
        subjectId = arguments?.getInt(TAG_INT) ?: -1
        vm.getQuestionCount(subject)
        vm.getNextQuestion(subject)
    }

    override fun onBind() {
        super.onBind()
        withBinding {
            navigationView.setContent(subject, true, false, false)
            AnswerOptionsRecyclerView.adapter = answersAdapter
            nextButton.text = getString(S.next)
        }
    }

    override fun observe() {
        observeLiveDataNonNull(vm.questionState) {
            with(binding) {
                questionTextView.text = it.questionTitle
                answersAdapter.submitList(it.answers.toList())
                nextButton.text =
                    getString(if (it.isLastQuestion) S.finish else S.next)
                nextButton.setOnClickListener(null)
                progressView.setProgress(it.questionIndex + 1)
            }
            answersAdapter.onItemClickListener {
                vm.checkAnswer(it, subject)
                answersAdapter.onItemClickListener(null)
            }

        }
        observeLiveDataNonNull(vm.answersListState) { checkedList ->
            answersAdapter.submitList(checkedList.toList())
            answersAdapter.notifyItemRangeChanged(0, answersAdapter.itemCount)
            binding.nextButton.setOnClickListener {
                vm.getNextQuestion(subject)
            }
        }
        observeLiveData(vm.pointsState) {
            points = it
            binding.progressView.setPoints(points)
        }

        observeLiveData(vm.isFinished) {
            if (it) showAlertDialog(points)

        }
        observeLiveData(vm.questionCount) {
            binding.progressView.setMaxValue(it)
        }
    }

    override fun setListeners() {
        binding.navigationView.onCloseButtonPressed {
            showPromptDialog()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            showPromptDialog()
        }
    }

    private fun showPromptDialog() {
        val dialog = promptDialog
            .setMessage(getString(S.stop_quiz_prompt))
            .setPositiveButton(getString(S.yes)) {
                vm.navigate(QuizFragmentDirections.HOME)
                it.dismiss()
            }.setNegativeButton(getString(S.no)) {
                it.dismiss()
            }.build() as QuizPromptDialogView
        dialog.show()
    }

    private fun showAlertDialog(score: Int) {
        val dialog = alertDialog
            .setTitle(getString(S.emoji_congrats))
            .setMessage(getString(S.congrats))
            .setDescription(String.format(getString(S.you_earned_points), score))
            .setButton(getString(S.close)) {
                vm.navigate(QuizFragmentDirections.HOME)
                it.dismiss()
                binding.progressView.clear()
            }.build() as QuizAlertDialogView
        dialog.show()
    }
}
