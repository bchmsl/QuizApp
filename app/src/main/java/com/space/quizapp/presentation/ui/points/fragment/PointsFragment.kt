package com.space.quizapp.presentation.ui.points.fragment

import com.space.quizapp.databinding.FragmentPointsBinding
import com.space.quizapp.presentation.base.fragment.BaseFragment
import com.space.quizapp.presentation.base.fragment.Inflater
import com.space.quizapp.presentation.ui.points.vm.PointsViewModel

class PointsFragment : BaseFragment<FragmentPointsBinding, PointsViewModel>() {
    override fun getViewModelClass() = PointsViewModel::class.java

    override fun inflate(): Inflater<FragmentPointsBinding> = FragmentPointsBinding::inflate

    override fun onBind() {
//        TODO
    }
}
