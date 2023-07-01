package com.space.quizapp.presentation.ui.ui_start.fragment

import androidx.core.widget.addTextChangedListener
import com.space.quizapp.common.extensions.coroutines.observeLiveData
import com.space.quizapp.common.extensions.utils.enable
import com.space.quizapp.common.extensions.utils.withBinding
import com.space.quizapp.common.util.Inflater
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
        }
    }

    override fun setListeners() {
        withBinding {
            startButton.setOnClickListener {
                vm.saveUser(usernameEditText.text.toString())
            }
            usernameEditText.addTextChangedListener {
                vm.validateUser(it.toString())
            }
        }
    }

    override fun setError(error: QuizCustomThrowable) {
        binding.usernameTextInputLayout.error = ""
        error.errorResource?.let {
            val errorString = when (it) {
                S.message_error_length_long -> getString(it, QuizUserValidation.MAXIMUM_LENGTH)
                S.message_error_length_short -> getString(it, QuizUserValidation.MINIMUM_LENGTH)
                else -> getString(it)
            }
            binding.usernameTextInputLayout.error = errorString
        }
    }
}
