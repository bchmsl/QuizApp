package com.space.quizapp.presentation.ui.home.fragment

import com.space.quizapp.databinding.FragmentHomeBinding
import com.space.quizapp.presentation.base.fragment.BaseFragment
import com.space.quizapp.presentation.base.fragment.Inflater
import com.space.quizapp.presentation.ui.home.vm.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun getViewModelClass() = HomeViewModel::class

    override fun inflate(): Inflater<FragmentHomeBinding> = FragmentHomeBinding::inflate

    override fun onBind() {
//    TODO
    }
}
