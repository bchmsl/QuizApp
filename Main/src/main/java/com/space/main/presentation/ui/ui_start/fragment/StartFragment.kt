package com.space.main.presentation.ui.ui_start.fragment

import android.os.Build
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import com.space.common.base.fragment.BaseFragment
import com.space.common.extensions.coroutines.observeLiveData
import com.space.common.extensions.utils.enable
import com.space.common.extensions.utils.withBinding
import com.space.common.util.Constants.EMPTY_STRING
import com.space.common.util.CustomThrowable
import com.space.common.util.Inflater
import com.space.common.util.UserValidation
import com.space.main.databinding.QuizFragmentStartBinding
import com.space.main.presentation.ui.ui_start.vm.StartViewModel
import kotlin.reflect.KClass

class StartFragment :
    BaseFragment<QuizFragmentStartBinding, StartViewModel>() {

    override val vmc: KClass<StartViewModel>
        get() = StartViewModel::class

    override fun inflate(): Inflater<QuizFragmentStartBinding> =
        QuizFragmentStartBinding::inflate

    override fun onFragmentCreate() {
        vm.checkUserToken()
    }

    override fun observe() {
        observeLiveData(vm.userValidState) { isValid ->
            binding.startButton.enable(isValid)
            if (isValid) {
                binding.usernameTextInputLayout.error = EMPTY_STRING
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setListeners() {
        withBinding {
            startButton.setOnClickListener {
                vm.saveUser(usernameEditText.text.toString())
            }
            usernameEditText.addTextChangedListener {
                if (it.isNullOrBlank()) {
                    binding.usernameTextInputLayout.error = EMPTY_STRING
                } else {
                    vm.validateUser(it.toString())
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            requireActivity().finish()
        }
    }

    override fun setError(error: CustomThrowable) {
        val errorString = when (val errorRes = error.errorResource) {
            com.space.common.util.S.message_error_length_long -> getString(
                errorRes,
                UserValidation.MAXIMUM_LENGTH
            )

            com.space.common.util.S.message_error_length_short -> getString(
                errorRes,
                UserValidation.MINIMUM_LENGTH
            )

            else -> errorRes?.let { getString(it) }
        }
        errorString?.let { binding.usernameTextInputLayout.error = it }

    }
}
