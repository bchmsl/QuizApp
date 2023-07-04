package com.space.quizapp.presentation.ui.home.fragment

import com.space.quizapp.common.extensions.collectAsync
import com.space.quizapp.common.extensions.withBinding
import com.space.quizapp.common.util.S
import com.space.quizapp.databinding.QuizFragmentHomeBinding
import com.space.quizapp.presentation.base.fragment.BaseFragment
import com.space.quizapp.presentation.base.fragment.Inflater
import com.space.quizapp.presentation.ui.home.vm.QuizHomeViewModel
import kotlin.reflect.KClass

class QuizHomeFragment : BaseFragment<QuizFragmentHomeBinding, QuizHomeViewModel>() {

    override val vmc: KClass<QuizHomeViewModel>
        get() = QuizHomeViewModel::class

    override fun inflate(): Inflater<QuizFragmentHomeBinding> = QuizFragmentHomeBinding::inflate

    override fun setContent() {
        vm.retrieveUserInfo()
    }

    override fun onBind() {
        setObservers()
        setListeners()
    }

    private fun setListeners() {
        withBinding {
            logOutFloatingButton.setOnClickListener {
                vm.logOut()
            }
        }
    }

    private fun setObservers() {
        collectAsync(vm.userState) { user ->
            withBinding {
                greetingTextView.text = getString(S.greeting).plus("${user.userName}!")
                scoreView.setContent(user.gpa)
            }
        }
    }
}
