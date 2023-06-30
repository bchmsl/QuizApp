package com.space.quizapp.presentation.ui.ui_points.fragment

import androidx.activity.addCallback
import com.space.quizapp.common.extensions.coroutines.observeLiveData
import com.space.quizapp.common.extensions.utils.gone
import com.space.quizapp.common.extensions.utils.visible
import com.space.quizapp.common.extensions.utils.withBinding
import com.space.quizapp.common.util.Inflater
import com.space.quizapp.common.util.S
import com.space.quizapp.databinding.QuizFragmentPointsBinding
import com.space.quizapp.presentation.base.fragment.QuizBaseFragment
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections
import com.space.quizapp.presentation.ui.common.view.dialog.QuizDialogFactory
import com.space.quizapp.presentation.ui.common.view.dialog.QuizPromptDialogView
import com.space.quizapp.presentation.ui.ui_points.adapter.QuizUserSubjectsAdapter
import com.space.quizapp.presentation.ui.ui_points.vm.QuizPointsViewModel
import kotlin.reflect.KClass

class QuizPointsFragment :
    QuizBaseFragment<QuizFragmentPointsBinding, QuizPointsViewModel>() {

    private val userSubjectsAdapter by lazy { QuizUserSubjectsAdapter() }
    private val dialog by lazy {
        QuizDialogFactory.createDialog(
            QuizDialogFactory.Dialog.DIALOG_PROMPT,
            requireContext()
        ) as QuizPromptDialogView.Builder
    }
    override val vmc: KClass<QuizPointsViewModel>
        get() = QuizPointsViewModel::class

    override fun inflate(): Inflater<QuizFragmentPointsBinding> = QuizFragmentPointsBinding::inflate

    override fun onFragmentCreate() {
        vm.getUserSubjects()
    }

    override fun onBind() {
        super.onBind()
        withBinding {
            navigationView.setContent(getString(S.points_earned), false, true, true)
            creditSubjectsRecyclerView.adapter = userSubjectsAdapter
        }

    }

    override fun setListeners() {
        binding.navigationView.onBackButtonPressed {
            navigate(QuizFragmentDirections.HOME)
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            navigate(QuizFragmentDirections.HOME)
        }
        binding.logOutFloatingButton.setOnClickListener {
            showDialog()
        }
    }

    override fun observe() {
        observeLiveData(vm.userSubjects) {
            if (it.isNullOrEmpty()) {
                setEmptyMessage()
            } else {
                userSubjectsAdapter.submitList(it.toList())
                with(binding) {
                    creditSubjectsRecyclerView.visible()
                    notEarnedTextView.gone()
                }
            }
        }
    }

    private fun setEmptyMessage() {
        with(binding) {
            notEarnedTextView.text = getString(S.not_earned_warning)
            creditSubjectsRecyclerView.gone()
            notEarnedTextView.visible()
        }
    }

    private fun showDialog() {
        dialog
            .setMessage(getString(S.exit_prompt))
            .setPositiveButton(getString(S.yes)) {
                vm.navigate(QuizFragmentDirections.START)
                it.dismiss()
            }.setNegativeButton(getString(S.no)) {
                it.dismiss()
            }.build()
            .show()
    }
}
