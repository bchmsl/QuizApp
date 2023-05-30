package com.space.quizapp.presentation.ui.points.fragment

import com.space.quizapp.databinding.FragmentPointsBinding
import com.space.quizapp.presentation.base.fragment.BaseFragment
import com.space.quizapp.presentation.base.fragment.Inflater
import com.space.quizapp.presentation.ui.points.vm.PointsViewModel
import kotlin.reflect.KClass

class PointsFragment : BaseFragment<FragmentPointsBinding, PointsViewModel>() {

    override val vmc: KClass<PointsViewModel>
        get() = PointsViewModel::class

    override fun inflate(): Inflater<FragmentPointsBinding> = FragmentPointsBinding::inflate

    override fun onBind() {
//        TODO
    }
}
