package com.space.quizapp.presentation.ui.ui_home.fragment

import androidx.activity.addCallback
import androidx.core.view.isVisible
import com.space.quizapp.common.extensions.coroutines.observeLiveData
import com.space.quizapp.common.extensions.coroutines.observeLiveDataNonNull
import com.space.quizapp.common.extensions.utils.withBinding
import com.space.quizapp.common.util.Inflater
import com.space.quizapp.common.util.S
import com.space.quizapp.databinding.QuizFragmentHomeBinding
import com.space.quizapp.presentation.base.fragment.QuizBaseFragment
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections
import com.space.quizapp.presentation.ui.ui_home.adapter.QuizSubjectsAdapter
import com.space.quizapp.presentation.ui.ui_home.vm.QuizHomeViewModel
import kotlin.reflect.KClass

class QuizHomeFragment : QuizBaseFragment<QuizFragmentHomeBinding, QuizHomeViewModel>() {

    private val subjectsAdapter by lazy { QuizSubjectsAdapter() }

    override val vmc: KClass<QuizHomeViewModel>
        get() = QuizHomeViewModel::class

    override fun inflate(): Inflater<QuizFragmentHomeBinding> = QuizFragmentHomeBinding::inflate

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
                greetingTextView.text = String.format(getString(S.greeting), user.userName)
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
                showPromptDialog(S.exit_prompt, onPositiveButton = {
                    vm.logOut()
                })
            }
            scoreView.setOnClickListener {
                navigate(QuizFragmentDirections.POINTS)
            }
        }
        subjectsAdapter.onItemClickListener {
            vm.navigate(QuizFragmentDirections.QUESTION, it.quizTitle)
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            showPromptDialog(S.exit_prompt, onPositiveButton = {
                vm.logOut()
            })
        }
    }
}
