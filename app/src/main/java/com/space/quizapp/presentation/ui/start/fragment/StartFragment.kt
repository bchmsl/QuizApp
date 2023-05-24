package com.space.quizapp.presentation.ui.start.fragment

import com.space.quizapp.databinding.FragmentStartBinding
import com.space.quizapp.presentation.base.fragment.BaseFragment
import com.space.quizapp.presentation.base.fragment.Inflater
import com.space.quizapp.presentation.ui.start.vm.StartViewModel

class StartFragment : BaseFragment<FragmentStartBinding, StartViewModel>() {
    override fun getViewModelClass() = StartViewModel::class.java

    override fun inflate(): Inflater<FragmentStartBinding> = FragmentStartBinding::inflate

    override fun onBind() {
//     TODO
    }
}
