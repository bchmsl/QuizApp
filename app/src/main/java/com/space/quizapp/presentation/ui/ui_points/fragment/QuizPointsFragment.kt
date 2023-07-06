package com.space.quizapp.presentation.ui.ui_points.fragment

import androidx.activity.addCallback
import com.space.common.base.fragment.QuizBaseFragment
import com.space.common.extensions.coroutines.observeLiveData
import com.space.common.extensions.utils.gone
import com.space.common.extensions.utils.visible
import com.space.common.extensions.utils.withBinding
import com.space.common.util.Inflater
import com.space.common.util.S
import com.space.quizapp.databinding.QuizFragmentPointsBinding
import com.space.quizapp.presentation.ui.ui_points.adapter.QuizUserSubjectsAdapter
import com.space.quizapp.presentation.ui.ui_points.vm.QuizPointsViewModel
import kotlin.reflect.KClass

class QuizPointsFragment :
    com.space.common.base.fragment.QuizBaseFragment<QuizFragmentPointsBinding, QuizPointsViewModel>() {

    private val userSubjectsAdapter by lazy { QuizUserSubjectsAdapter() }

    override val vmc: KClass<QuizPointsViewModel>
        get() = QuizPointsViewModel::class

    override fun inflate(): com.space.common.util.Inflater<QuizFragmentPointsBinding> =
        QuizFragmentPointsBinding::inflate

    override fun onFragmentCreate() {
        vm.getUserSubjects()
    }

    override fun onBind() {
        super.onBind()
        withBinding {
            navigationView.setContent(
                getString(com.space.common.util.S.points_earned),
                closeAvailable = false,
                backAvailable = true,
                starAvailable = true
            )
            userSubjectsRecyclerView.adapter = userSubjectsAdapter
        }

    }

    override fun setListeners() {
        binding.navigationView.onBackButtonPressed {
            vm.navigateToHome()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            vm.navigateToHome()
        }
        binding.logOutFloatingButton.setOnClickListener {
            showPromptDialog(com.space.common.util.S.exit_prompt, onPositiveButton = {
                vm.logOut()
            })
        }
        userSubjectsAdapter.onItemClickListener {
            if (it.score != it.maxScore) {
                vm.navigateToQuestion(it.quizTitle)
            }
        }
    }

    override fun observe() {
        observeLiveData(vm.userSubjects) {
            if (it.isNullOrEmpty()) {
                setEmptyMessage()
            } else {
                userSubjectsAdapter.submitList(it.toList())
                with(binding) {
                    userSubjectsRecyclerView.visible()
                    notEarnedTextView.gone()
                }
            }
        }
    }

    private fun setEmptyMessage() {
        with(binding) {
            notEarnedTextView.text = getString(com.space.common.util.S.not_earned_warning)
            userSubjectsRecyclerView.gone()
            notEarnedTextView.visible()
        }
    }
}
