package com.space.quizapp.presentation.ui.ui_question.fragment

import com.space.quizapp.common.extensions.coroutines.collectAsync
import com.space.quizapp.common.extensions.utils.withBinding
import com.space.quizapp.common.util.S
import com.space.quizapp.databinding.QuizFragmentQuestionBinding
import com.space.quizapp.presentation.base.fragment.BaseFragment
import com.space.quizapp.presentation.base.fragment.Inflater
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections.Companion.TAG_INT
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections.Companion.TAG_STRING
import com.space.quizapp.presentation.ui.ui_question.adapter.AnswersAdapter
import com.space.quizapp.presentation.ui.ui_question.vm.QuizQuestionViewModel
import kotlin.reflect.KClass

class QuizQuestionFragment : BaseFragment<QuizFragmentQuestionBinding, QuizQuestionViewModel>() {

    private val answersAdapter by lazy { AnswersAdapter() }

    override val vmc: KClass<QuizQuestionViewModel>
        get() = QuizQuestionViewModel::class

    override fun inflate(): Inflater<QuizFragmentQuestionBinding> =
        QuizFragmentQuestionBinding::inflate

    override fun setContent() {
        val subject = arguments?.getString(TAG_STRING).toString()
        val subjectId = arguments?.getInt(TAG_INT) ?: -1
        vm.retrieveQuestions(subjectId)
        withBinding {
            navigationView.setContent(subject, true, false)
            AnswerOptionsRecyclerView.adapter = answersAdapter
            nextButton.text = getString(S.next)
        }

    }

    private fun setListeners() {
        binding.nextButton.setOnClickListener {
            vm.getNextQuestion()
        }
        answersAdapter.onItemClickListener {
            vm.checkAnswer(it)
        }
    }

    private fun observe() {
        collectAsync(vm.questionState) { question ->
            binding.questionTextView.text = question?.questionTitle
            answersAdapter.submitList(question?.answers?.toList())
        }
        collectAsync(vm.checkedAnswersState) {
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
