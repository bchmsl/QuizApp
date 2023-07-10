package com.space.main.presentation.ui.ui_start.fragment

import android.os.Build
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import com.space.common.base.fragment.QuizBaseFragment
import com.space.common.extensions.coroutines.observeLiveData
import com.space.common.extensions.utils.enable
import com.space.common.extensions.utils.withBinding
import com.space.common.util.Inflater
import com.space.common.util.QuizConstants.EMPTY_STRING
import com.space.common.util.QuizCustomThrowable
import com.space.common.util.QuizUserValidation
import com.space.main.databinding.QuizFragmentStartBinding
import com.space.main.presentation.ui.ui_start.vm.QuizStartViewModel
import kotlin.reflect.KClass

class QuizStartFragment :
    QuizBaseFragment<QuizFragmentStartBinding, QuizStartViewModel>() {

    override val vmc: KClass<QuizStartViewModel>
        get() = QuizStartViewModel::class

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

    override fun setError(error: QuizCustomThrowable) {
        val errorString = when (val errorRes = error.errorResource) {
            com.space.common.util.S.message_error_length_long -> getString(
                errorRes,
                QuizUserValidation.MAXIMUM_LENGTH
            )

            com.space.common.util.S.message_error_length_short -> getString(
                errorRes,
                QuizUserValidation.MINIMUM_LENGTH
            )

            else -> errorRes?.let { getString(it) }
        }
        errorString?.let { binding.usernameTextInputLayout.error = it }

    }
}
