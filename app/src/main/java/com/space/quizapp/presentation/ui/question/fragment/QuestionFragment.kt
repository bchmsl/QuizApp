package com.space.quizapp.presentation.ui.question.fragment

import com.space.quizapp.databinding.FragmentQuestionBinding
import com.space.quizapp.presentation.base.fragment.BaseFragment
import com.space.quizapp.presentation.base.fragment.Inflater
import com.space.quizapp.presentation.ui.question.vm.QuestionViewModel
import kotlin.reflect.KClass

class QuestionFragment : BaseFragment<FragmentQuestionBinding, QuestionViewModel>() {

    override val vmc: KClass<QuestionViewModel>
        get() = QuestionViewModel::class

    override fun inflate(): Inflater<FragmentQuestionBinding> = FragmentQuestionBinding::inflate

    override fun onBind() {
//        TODO
    }
}
