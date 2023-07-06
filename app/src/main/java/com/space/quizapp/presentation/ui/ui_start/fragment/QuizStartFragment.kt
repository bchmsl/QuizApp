package com.space.quizapp.presentation.ui.ui_start.fragment

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
import com.space.common.util.S
import com.space.quizapp.databinding.QuizFragmentStartBinding
import com.space.quizapp.presentation.ui.ui_start.vm.QuizStartViewModel
import kotlin.reflect.KClass

class QuizStartFragment :
    com.space.common.base.fragment.QuizBaseFragment<QuizFragmentStartBinding, QuizStartViewModel>() {

    override val vmc: KClass<QuizStartViewModel>
        get() = QuizStartViewModel::class

    override fun inflate(): com.space.common.util.Inflater<QuizFragmentStartBinding> =
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

    override fun setError(error: com.space.common.util.QuizCustomThrowable) {
        val errorString = when (val errorRes = error.errorResource) {
            com.space.common.util.S.message_error_length_long -> getString(
                errorRes,
                com.space.common.util.QuizUserValidation.MAXIMUM_LENGTH
            )

            com.space.common.util.S.message_error_length_short -> getString(
                errorRes,
                com.space.common.util.QuizUserValidation.MINIMUM_LENGTH
            )

            else -> errorRes?.let { getString(it) }
        }
        errorString?.let { binding.usernameTextInputLayout.error = it }

    }
}
