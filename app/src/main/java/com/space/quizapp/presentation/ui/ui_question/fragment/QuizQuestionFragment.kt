package com.space.quizapp.presentation.ui.ui_question.fragment

import androidx.activity.addCallback
import com.space.quizapp.common.extensions.coroutines.observeLiveDataNonNull
import com.space.quizapp.common.extensions.utils.withBinding
import com.space.quizapp.common.util.Inflater
import com.space.quizapp.common.util.S
import com.space.quizapp.databinding.QuizFragmentQuestionBinding
import com.space.quizapp.presentation.base.fragment.QuizBaseFragment
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections.Companion.TAG_INT
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections.Companion.TAG_STRING
import com.space.quizapp.presentation.ui.common.view.dialog.QuizDialogAlertView
import com.space.quizapp.presentation.ui.common.view.dialog.QuizDialogPromptView
import com.space.quizapp.presentation.ui.ui_question.adapter.AnswersAdapter
import com.space.quizapp.presentation.ui.ui_question.vm.QuizQuestionViewModel
import kotlin.reflect.KClass

class QuizQuestionFragment :
    QuizBaseFragment<QuizFragmentQuestionBinding, QuizQuestionViewModel>() {

    private val answersAdapter by lazy { AnswersAdapter() }
    private val promptDialog by lazy { QuizDialogPromptView(requireContext()) }
    private val alertDialog by lazy { QuizDialogAlertView(requireContext()) }

    private lateinit var subject: String
    private var subjectId: Int = -1

    override val vmc: KClass<QuizQuestionViewModel>
        get() = QuizQuestionViewModel::class

    override fun inflate(): Inflater<QuizFragmentQuestionBinding> =
        QuizFragmentQuestionBinding::inflate

    override fun onFragmentCreate() {
        subject = arguments?.getString(TAG_STRING).toString()
        subjectId = arguments?.getInt(TAG_INT) ?: -1
        vm.getNextQuestion(subject)
    }

    override fun onBind() {
        super.onBind()
        vm.resetUserPoints()
        withBinding {
            navigationView.setContent(subject, true, false)
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
            }
            answersAdapter.onItemClickListener {
                vm.checkAnswer(it, subject)
                answersAdapter.onItemClickListener(null)
            }
        }
        observeLiveDataNonNull(vm.answersListState) { checkedList ->
            answersAdapter.submitList(checkedList.toList())
            binding.nextButton.setOnClickListener {
                vm.getNextQuestion(subject)
            }
        }
        observeLiveDataNonNull(vm.pointsState) {
            showAlertDialog(it)
            vm.saveUserSubject(subject, it)
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
        promptDialog
            .setContent(getString(S.stop_quiz_prompt))
            .onPositiveButtonListener {
                vm.navigate(QuizFragmentDirections.HOME)
                promptDialog.dismiss()
            }.onNegativeButtonListener {
                promptDialog.dismiss()
            }.show()
    }

    private fun showAlertDialog(score: Int) {
        alertDialog
            .setContent(
                getString(S.emoji_congrats),
                getString(S.congrats),
                String.format(getString(S.you_earned_points), score)
            )
            .onButtonClick {
                vm.navigate(QuizFragmentDirections.HOME)
                alertDialog.dismiss()
            }.show()
    }
}
