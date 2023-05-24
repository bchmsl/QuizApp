package com.space.quizapp.presentation.ui.question.fragment

import com.space.quizapp.databinding.FragmentQuestionBinding
import com.space.quizapp.presentation.base.fragment.BaseFragment
import com.space.quizapp.presentation.base.fragment.Inflater
import com.space.quizapp.presentation.ui.question.vm.QuestionViewModel

class QuestionFragment : BaseFragment<FragmentQuestionBinding, QuestionViewModel>() {
    override fun getViewModelClass() = QuestionViewModel::class.java

    override fun inflate(): Inflater<FragmentQuestionBinding> = FragmentQuestionBinding::inflate

    override fun onBind() {
//        TODO
    }
}
