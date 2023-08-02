package com.space.main.presentation.ui.ui_points.fragment

import androidx.activity.addCallback
import com.space.common.extensions.coroutines.observeLiveData
import com.space.common.extensions.utils.gone
import com.space.common.extensions.utils.visible
import com.space.common.extensions.utils.withBinding
import com.space.common.util.Inflater
import com.space.common.util.S
import com.space.main.databinding.QuizFragmentPointsBinding
import com.space.main.presentation.ui.ui_points.adapter.UserSubjectsAdapter
import com.space.main.presentation.ui.ui_points.vm.PointsViewModel
import kotlin.reflect.KClass

class PointsFragment :
    com.space.common.base.fragment.BaseFragment<QuizFragmentPointsBinding, PointsViewModel>() {

    private val userSubjectsAdapter by lazy { UserSubjectsAdapter() }

    override val vmc: KClass<PointsViewModel>
        get() = PointsViewModel::class

    override fun inflate(): Inflater<QuizFragmentPointsBinding> =
        QuizFragmentPointsBinding::inflate

    override fun onFragmentCreate() {
        vm.getUserSubjects()
    }

    override fun onBind() {
        super.onBind()
        withBinding {
            navigationView.setContent(
                getString(S.points_earned),
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
            notEarnedTextView.text = getString(S.not_earned_warning)
            userSubjectsRecyclerView.gone()
            notEarnedTextView.visible()
        }
    }
}
