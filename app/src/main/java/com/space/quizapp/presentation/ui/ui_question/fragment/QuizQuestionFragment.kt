package com.space.quizapp.presentation.ui.ui_question.fragment

import androidx.activity.addCallback
import com.space.quizapp.common.extensions.coroutines.collectAsync
import com.space.quizapp.common.extensions.utils.withBinding
import com.space.quizapp.common.util.Inflater
import com.space.quizapp.common.util.S
import com.space.quizapp.databinding.QuizFragmentQuestionBinding
import com.space.quizapp.presentation.base.fragment.QuizBaseFragment
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections.Companion.TAG_INT
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections.Companion.TAG_STRING
import com.space.quizapp.presentation.ui.common.view.dialog.QuizDialogPromptView
import com.space.quizapp.presentation.ui.ui_question.adapter.AnswersAdapter
import com.space.quizapp.presentation.ui.ui_question.vm.QuizQuestionViewModel
import kotlin.reflect.KClass

class QuizQuestionFragment :
    QuizBaseFragment<QuizFragmentQuestionBinding, QuizQuestionViewModel>() {

    private val answersAdapter by lazy { AnswersAdapter() }
    private val dialog by lazy { QuizDialogPromptView(requireContext()) }

    private lateinit var subject: String
    private var subjectId: Int = -1

    override val vmc: KClass<QuizQuestionViewModel>
        get() = QuizQuestionViewModel::class

    override fun inflate(): Inflater<QuizFragmentQuestionBinding> =
        QuizFragmentQuestionBinding::inflate

    override fun onFragmentCreate() {
        subject = arguments?.getString(TAG_STRING).toString()
        subjectId = arguments?.getInt(TAG_INT) ?: -1
        vm.retrieveQuestions(subjectId)
    }

    override fun onBind() {
        super.onBind()
        withBinding {
            navigationView.setContent(subject, true, false)
            AnswerOptionsRecyclerView.adapter = answersAdapter
            nextButton.text = getString(S.next)
        }
    }

    override fun observe() {
        collectAsync(vm.questionState) { question ->
            question?.let {
                binding.questionTextView.text = question.questionModel.questionTitle
                answersAdapter.submitList(question.questionModel.answers.toList())
                binding.nextButton.text =
                    getString(if (question.isLastQuestion) S.finish else S.next)
                binding.nextButton.setOnClickListener(null)
            }
        }

        collectAsync(vm.checkedAnswersState) {
            it?.let {
                answersAdapter.submitList(it.toList())
            }
            binding.nextButton.setOnClickListener {
                vm.getNextQuestion()
            }
        }
    }

    override fun setListeners() {
        answersAdapter.onItemClickListener {
            vm.checkAnswer(it)
        }
        binding.navigationView.onCloseButtonPressed {
            showDialog()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            showDialog()
        }
    }

    private fun showDialog() {
        dialog
            .setContent(getString(S.stop_quiz_prompt))
            .onPositiveButtonListener {
                vm.navigate(QuizFragmentDirections.HOME)
                dialog.dismiss()
            }.onNegativeButtonListener {
                dialog.dismiss()
            }.show()
    }
}
