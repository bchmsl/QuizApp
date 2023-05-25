package com.space.quizapp.presentation.ui.start.fragment

import androidx.core.widget.addTextChangedListener
import com.space.quizapp.common.extensions.collectAsync
import com.space.quizapp.common.extensions.withBinding
import com.space.quizapp.databinding.FragmentStartBinding
import com.space.quizapp.presentation.base.fragment.BaseFragment
import com.space.quizapp.presentation.base.fragment.Inflater
import com.space.quizapp.presentation.ui.start.vm.StartViewModel

class StartFragment : BaseFragment<FragmentStartBinding, StartViewModel>() {
    override fun getViewModelClass() = StartViewModel::class

    override fun inflate(): Inflater<FragmentStartBinding> = FragmentStartBinding::inflate

    override fun onBind() {
        setObservers()
        setListeners()
    }

    private fun setObservers() {
        collectAsync(vm.usernameErrorState) {
            setErrorText(it?.message)
        }
    }

    private fun setErrorText(error: Int?) {
        withBinding {
            if (error != null) {
                usernameTextInputLayout.error = getString(error)
            } else {
                usernameTextInputLayout.error = error
            }
        }
    }

    private fun setListeners() {
        withBinding {
            startButton.setOnClickListener {
                vm.saveUser(usernameEditText.text.toString())
            }
            usernameEditText.addTextChangedListener {
                usernameTextInputLayout.error = null
            }
        }
    }
}
