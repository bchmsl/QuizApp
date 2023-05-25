package com.space.quizapp.presentation.ui.start.fragment

import android.util.Log
import androidx.core.widget.addTextChangedListener
import com.space.quizapp.common.extensions.collectAsync
import com.space.quizapp.common.extensions.withBinding
import com.space.quizapp.databinding.FragmentStartBinding
import com.space.quizapp.presentation.base.fragment.BaseFragment
import com.space.quizapp.presentation.base.fragment.Inflater
import com.space.quizapp.presentation.ui.start.vm.StartViewModel

class StartFragment : BaseFragment<FragmentStartBinding, StartViewModel>() {
    override fun getViewModelClass() = StartViewModel::class.java

    override fun inflate(): Inflater<FragmentStartBinding> = FragmentStartBinding::inflate

    override fun onBind() {
        setObservers()
        setListeners()
    }

    private fun setObservers() {
        collectAsync(vm.usernameState) {
            Log.d("TAG", "$it")
            it?.let {
                setErrorText(it.message)
                return@collectAsync
            }
        }
    }

    private fun setErrorText(error: Int?) {
        withBinding {
            usernameTextInputLayout.error = error?.let { getString(it) }
        }
    }

    private fun setListeners() {
        withBinding {
            startButton.setOnClickListener {
                vm.saveUser(usernameEditText.text.toString())
            }
            usernameEditText.addTextChangedListener {
                usernameEditText.error = null
            }
        }
    }
}
