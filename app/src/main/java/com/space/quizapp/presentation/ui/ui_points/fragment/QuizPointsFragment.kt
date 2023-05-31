package com.space.quizapp.presentation.ui.ui_points.fragment

import com.space.quizapp.databinding.QuizFragmentPointsBinding
import com.space.quizapp.presentation.base.fragment.BaseFragment
import com.space.quizapp.presentation.base.fragment.Inflater
import com.space.quizapp.presentation.ui.ui_points.vm.QuizPointsViewModel
import kotlin.reflect.KClass

class QuizPointsFragment : BaseFragment<QuizFragmentPointsBinding, QuizPointsViewModel>() {

    override val vmc: KClass<QuizPointsViewModel>
        get() = QuizPointsViewModel::class

    override fun inflate(): Inflater<QuizFragmentPointsBinding> = QuizFragmentPointsBinding::inflate

    override fun setContent() {
//        TODO
    }

    override fun onBind() {
//        TODO
    }
}
