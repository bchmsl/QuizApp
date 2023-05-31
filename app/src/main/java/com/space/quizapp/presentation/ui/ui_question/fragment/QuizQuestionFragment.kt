package com.space.quizapp.presentation.ui.ui_question.fragment

import android.util.Log
import com.space.quizapp.common.extensions.coroutines.collectAsync
import com.space.quizapp.databinding.QuizFragmentQuestionBinding
import com.space.quizapp.presentation.base.fragment.BaseFragment
import com.space.quizapp.presentation.base.fragment.Inflater
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections.Companion.TAG
import com.space.quizapp.presentation.ui.ui_question.vm.QuizQuestionViewModel
import kotlin.reflect.KClass

class QuizQuestionFragment : BaseFragment<QuizFragmentQuestionBinding, QuizQuestionViewModel>() {

    override val vmc: KClass<QuizQuestionViewModel>
        get() = QuizQuestionViewModel::class

    override fun inflate(): Inflater<QuizFragmentQuestionBinding> =
        QuizFragmentQuestionBinding::inflate

    override fun setContent() {
        arguments?.getString(TAG)?.let { vm.retrieveQuestions(it) }
    }

    fun observe() {
        collectAsync(vm.questionsState) {
            it.forEach {
                Log.d("TAG", it.questionTitle)
            }
        }
    }

    override fun onBind() {
        observe()
    }
}
