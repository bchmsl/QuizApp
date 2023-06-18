package com.space.quizapp.presentation.ui.ui_points.fragment

import androidx.activity.addCallback
import com.space.quizapp.common.extensions.utils.visible
import com.space.quizapp.common.util.Inflater
import com.space.quizapp.common.util.S
import com.space.quizapp.databinding.QuizFragmentPointsBinding
import com.space.quizapp.presentation.base.fragment.QuizBaseFragment
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections
import com.space.quizapp.presentation.ui.ui_points.vm.QuizPointsViewModel
import kotlin.reflect.KClass

class QuizPointsFragment :
    QuizBaseFragment<QuizFragmentPointsBinding, QuizPointsViewModel>() {

    override val vmc: KClass<QuizPointsViewModel>
        get() = QuizPointsViewModel::class

    override fun inflate(): Inflater<QuizFragmentPointsBinding> = QuizFragmentPointsBinding::inflate

    override fun onFragmentCreate() {
//        TODO("Not yet implemented")
    }

    override fun onBind() {
        binding.navigationView.setContent(getString(S.points_earned), false, true)
        binding.notEarnedTextView.text = getString(S.not_earned_warning)
        binding.notEarnedTextView.visible()
    }

    override fun setListeners() {
        binding.navigationView.onBackButtonPressed {
            navigate(QuizFragmentDirections.HOME)
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            navigate(QuizFragmentDirections.HOME)
        }
    }

    override fun observe() {
//        TODO("Not yet implemented")
    }
}
