package com.space.main_impl.presentation.ui.ui_home.fragment

import androidx.activity.addCallback
import androidx.core.view.isVisible
import com.space.common.extensions.coroutines.observeLiveData
import com.space.common.extensions.coroutines.observeLiveDataNonNull
import com.space.common.extensions.utils.withBinding
import com.space.main_impl.databinding.QuizFragmentHomeBinding
import com.space.main_impl.presentation.ui.ui_home.adapter.QuizSubjectsAdapter
import com.space.main_impl.presentation.ui.ui_home.vm.QuizHomeViewModel
import kotlin.reflect.KClass

class QuizHomeFragment :
    com.space.common.base.fragment.QuizBaseFragment<QuizFragmentHomeBinding, QuizHomeViewModel>() {

    private val subjectsAdapter by lazy { QuizSubjectsAdapter() }

    override val vmc: KClass<QuizHomeViewModel>
        get() = QuizHomeViewModel::class

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
