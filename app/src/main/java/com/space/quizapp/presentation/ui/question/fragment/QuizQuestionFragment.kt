package com.space.quizapp.presentation.ui.question.fragment

import com.space.quizapp.databinding.QuizFragmentQuestionBinding
import com.space.quizapp.presentation.base.fragment.BaseFragment
import com.space.quizapp.presentation.base.fragment.Inflater
import com.space.quizapp.presentation.ui.question.vm.QuizQuestionViewModel
import kotlin.reflect.KClass

class QuizQuestionFragment : BaseFragment<QuizFragmentQuestionBinding, QuizQuestionViewModel>() {

    override val vmc: KClass<QuizQuestionViewModel>
        get() = QuizQuestionViewModel::class

    override fun inflate(): Inflater<QuizFragmentQuestionBinding> =
        QuizFragmentQuestionBinding::inflate

    override fun onBind() {
//        TODO
    }
}
