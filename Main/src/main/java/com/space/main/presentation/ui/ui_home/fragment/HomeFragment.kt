package com.space.main.presentation.ui.ui_home.fragment

import androidx.activity.addCallback
import androidx.core.view.isVisible
import com.space.common.extensions.coroutines.observeLiveData
import com.space.common.extensions.coroutines.observeLiveDataNonNull
import com.space.common.extensions.utils.withBinding
import com.space.main.databinding.QuizFragmentHomeBinding
import com.space.main.presentation.ui.ui_home.adapter.SubjectsAdapter
import com.space.main.presentation.ui.ui_home.vm.HomeViewModel
import kotlin.reflect.KClass

class HomeFragment :
    com.space.common.base.fragment.BaseFragment<QuizFragmentHomeBinding, HomeViewModel>() {

    private val subjectsAdapter by lazy { SubjectsAdapter() }

    override val vmc: KClass<HomeViewModel>
        get() = HomeViewModel::class

    override fun inflate(): com.space.common.util.Inflater<QuizFragmentHomeBinding> =
        QuizFragmentHomeBinding::inflate

    override fun onFragmentCreate() {
        vm.retrieveUserInfo()
        vm.retrieveSubjects()
    }

    override fun onBind() {
        super.onBind()
        binding.subjectsRecyclerView.adapter = subjectsAdapter
    }

    override fun observe() {
        observeLiveDataNonNull(vm.userState) { user ->
            withBinding {
                greetingTextView.text =
                    String.format(getString(com.space.common.util.S.greeting), user.userName)
                scoreView.setGpa(user.gpa)
            }
        }

        observeLiveDataNonNull(vm.subjectsState) { questions ->
            subjectsAdapter.submitList(questions.toList())
        }

        observeLiveData(vm.loadingState) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }
    }

    override fun setListeners() {
        withBinding {
            logOutFloatingButton.setOnClickListener {
                showPromptDialog(com.space.common.util.S.exit_prompt, onPositiveButton = {
                    vm.logOut()
                })
            }
            scoreView.setOnClickListener {
                vm.navigateToPoints()
            }
        }
        subjectsAdapter.onItemClickListener {
            vm.navigateToQuestion(it.quizTitle)
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            showPromptDialog(com.space.common.util.S.exit_prompt, onPositiveButton = {
                vm.logOut()
            })
        }
    }
}
