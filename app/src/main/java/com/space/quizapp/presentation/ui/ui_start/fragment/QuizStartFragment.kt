package com.space.quizapp.presentation.ui.ui_start.fragment

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import com.space.quizapp.common.extensions.coroutines.observeLiveData
import com.space.quizapp.common.extensions.utils.enable
import com.space.quizapp.common.extensions.utils.withBinding
import com.space.quizapp.common.util.Inflater
import com.space.quizapp.common.util.QuizConstants.EMPTY_STRING
import com.space.quizapp.common.util.QuizCustomThrowable
import com.space.quizapp.common.util.QuizUserValidation
import com.space.quizapp.common.util.S
import com.space.quizapp.databinding.QuizFragmentStartBinding
import com.space.quizapp.presentation.base.fragment.QuizBaseFragment
import com.space.quizapp.presentation.ui.ui_start.vm.QuizStartViewModel
import kotlin.reflect.KClass

class QuizStartFragment : QuizBaseFragment<QuizFragmentStartBinding, QuizStartViewModel>() {

    override val vmc: KClass<QuizStartViewModel>
        get() = QuizStartViewModel::class

    override fun inflate(): Inflater<QuizFragmentStartBinding> = QuizFragmentStartBinding::inflate

    override fun onFragmentCreate() {
        vm.checkUserToken()
    }

    override fun observe() {
        observeLiveData(vm.userValidState) { isValid ->
            binding.startButton.enable(isValid)
            if (isValid) {
                setError(EMPTY_STRING)
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
                    setError(EMPTY_STRING)
                } else {
                    vm.validateUser(it.toString())
                }
            }
        }
    }

    override fun setError(error: QuizCustomThrowable) {
        val errorString = when (val errorRes = error.errorResource) {
            S.message_error_length_long -> getString(errorRes, QuizUserValidation.MAXIMUM_LENGTH)
            S.message_error_length_short -> getString(errorRes, QuizUserValidation.MINIMUM_LENGTH)
            else -> errorRes?.let { getString(it) }
        }
        errorString?.let { setError(it) }

    }

    private fun setError(error: String) {
        binding.usernameTextInputLayout.error = error
    }
}
