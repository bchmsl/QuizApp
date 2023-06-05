package com.space.quizapp.presentation.ui.ui_question.fragment

import com.space.quizapp.common.extensions.coroutines.collectAsync
import com.space.quizapp.common.extensions.utils.log
import com.space.quizapp.common.extensions.utils.withBinding
import com.space.quizapp.common.util.S
import com.space.quizapp.databinding.QuizFragmentQuestionBinding
import com.space.quizapp.presentation.base.fragment.BaseFragment
import com.space.quizapp.presentation.base.fragment.Inflater
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections.Companion.TAG
import com.space.quizapp.presentation.ui.ui_question.adapter.AnswersAdapter
import com.space.quizapp.presentation.ui.ui_question.model.AnswerModel
import com.space.quizapp.presentation.ui.ui_question.vm.QuizQuestionViewModel
import kotlin.reflect.KClass

class QuizQuestionFragment : BaseFragment<QuizFragmentQuestionBinding, QuizQuestionViewModel>() {

    private val answersAdapter by lazy { AnswersAdapter() }

    override val vmc: KClass<QuizQuestionViewModel>
        get() = QuizQuestionViewModel::class

    override fun inflate(): Inflater<QuizFragmentQuestionBinding> =
        QuizFragmentQuestionBinding::inflate

    override fun setContent() {
        val category = arguments?.getString(TAG).toString()
        log(category)
        vm.getQuestions(category)
        withBinding {
            navigationView.setContent(category, true, false)
            AnswerOptionsRecyclerView.adapter = answersAdapter
            nextButton.text = getString(S.next)
        }

    }

    private fun setListeners() {
        binding.nextButton.setOnClickListener {
            vm.getNextQuestion()
        }
        answersAdapter.onItemClickListener {
            vm.checkAnswer(it.answerOption, answersAdapter.currentList.toMutableList())
        }
    }

    private fun observe() {
        collectAsync(vm.questionsState) { question ->
            question?.let {
                log(question, "QuizQuestionFragment - observe()")
                answersAdapter.submitList(it.answers.map { answer -> AnswerModel(answer) })
                binding.questionTextView.text = it.questionTitle
            }
        }
        collectAsync(vm.checkedAnswerState) {
            it?.let {
                answersAdapter.submitList(it.toList())
            }
        }
    }

    override fun onBind() {
        observe()
        setListeners()
    }
}
