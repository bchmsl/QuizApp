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
import com.space.quizapp.presentation.ui.common.view.dialog.QuizDialogFactory
import com.space.quizapp.presentation.ui.common.view.dialog.QuizPromptDialogView
import com.space.quizapp.presentation.ui.ui_home.adapter.QuizSubjectsAdapter
import com.space.quizapp.presentation.ui.ui_home.vm.QuizHomeViewModel
import kotlin.reflect.KClass

class QuizHomeFragment : QuizBaseFragment<QuizFragmentHomeBinding, QuizHomeViewModel>() {

    private val subjectsAdapter by lazy { QuizSubjectsAdapter() }
    private val dialog by lazy {
        QuizDialogFactory.createDialog(
            QuizDialogFactory.Dialog.DIALOG_PROMPT,
            requireContext()
        ) as QuizPromptDialogView.Builder
    }

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
                scoreView.setContent(user.gpa)
            }
        }
        observeLiveDataNonNull(vm.subjectsState) { questions ->
            subjectsAdapter.submitList(questions.toList())
        }
        observeLiveData(vm.loadingState) {
            binding.progressBar.isVisible = it
        }

    }

    override fun setListeners() {
        withBinding {
            logOutFloatingButton.setOnClickListener {
                showDialog()
            }
            scoreView.setOnClickListener {
                navigate(QuizFragmentDirections.POINTS)
            }
        }
        subjectsAdapter.onItemClickListener {
            vm.navigate(QuizFragmentDirections.QUESTION, it.id, it.quizTitle)
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            showDialog()
        }
    }

    private fun showDialog() {
        val dialog = dialog
            .setMessage(getString(S.exit_prompt))
            .setPositiveButton(getString(S.yes)) {
                vm.logOut()
                vm.navigate(QuizFragmentDirections.START)
                it.dismiss()
            }.setNegativeButton(getString(S.no)) {
                it.dismiss()
            }.build() as QuizPromptDialogView
        dialog.show()
    }
}
