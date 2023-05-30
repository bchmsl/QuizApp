package com.space.quizapp.presentation.ui.start.fragment

import androidx.core.widget.addTextChangedListener
import com.space.quizapp.common.extensions.withBinding
import com.space.quizapp.databinding.FragmentStartBinding
import com.space.quizapp.presentation.base.fragment.BaseFragment
import com.space.quizapp.presentation.base.fragment.Inflater
import com.space.quizapp.presentation.ui.start.vm.StartViewModel
import kotlin.reflect.KClass

class StartFragment : BaseFragment<FragmentStartBinding, StartViewModel>() {

    override val vmc: KClass<StartViewModel>
        get() = StartViewModel::class

    override fun inflate(): Inflater<FragmentStartBinding> = FragmentStartBinding::inflate

    override fun onBind() {
        vm.checkUserToken()
        setListeners()
    }

    override fun setError(error: Any) {
        val errorMessage = getString(error as Int)
        binding.usernameTextInputLayout.error = errorMessage
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
