package com.space.quizapp.presentation.ui.home.fragment

import com.space.quizapp.common.extensions.collectAsync
import com.space.quizapp.common.extensions.withBinding
import com.space.quizapp.common.util.S
import com.space.quizapp.databinding.FragmentHomeBinding
import com.space.quizapp.presentation.base.fragment.BaseFragment
import com.space.quizapp.presentation.base.fragment.Inflater
import com.space.quizapp.presentation.ui.home.vm.HomeViewModel
import kotlin.reflect.KClass

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val vmc: KClass<HomeViewModel>
        get() = HomeViewModel::class

    override fun inflate(): Inflater<FragmentHomeBinding> = FragmentHomeBinding::inflate

    override fun onBind() {
        vm.retrieveUserInfo()
        setObservers()
    }

    private fun setObservers() {
        collectAsync(vm.userState) { user ->
            withBinding {
                greetingTextView.text = getString(S.greeting).plus("${user.userName}!")
                scoreSection.gpaTextView.text = user.gpa.toString()
            }
        }
    }
}
