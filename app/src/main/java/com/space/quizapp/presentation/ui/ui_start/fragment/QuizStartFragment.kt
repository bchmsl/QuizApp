package com.space.quizapp.presentation.ui.ui_start.fragment

import androidx.core.widget.addTextChangedListener
import com.space.quizapp.common.extensions.utils.withBinding
import com.space.quizapp.common.util.Inflater
import com.space.quizapp.common.util.QuizCustomThrowable
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

    override fun setListeners() {
        withBinding {
            startButton.setOnClickListener {
                vm.saveUser(usernameEditText.text.toString())
            }
            usernameEditText.addTextChangedListener {
                usernameTextInputLayout.error = ""
            }
        }
    }

    override fun setError(error: QuizCustomThrowable) {
        val errorMessage = error.errorResource?.let { getString(it) }
        binding.usernameTextInputLayout.error = errorMessage
    }
}
