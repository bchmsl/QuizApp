package com.space.quizapp.presentation.ui.start.fragment

import androidx.core.widget.addTextChangedListener
import com.space.quizapp.common.extensions.withBinding
import com.space.quizapp.databinding.QuizFragmentStartBinding
import com.space.quizapp.presentation.base.fragment.BaseFragment
import com.space.quizapp.presentation.base.fragment.Inflater
import com.space.quizapp.presentation.ui.start.vm.QuizStartViewModel
import kotlin.reflect.KClass

class QuizStartFragment : BaseFragment<QuizFragmentStartBinding, QuizStartViewModel>() {

    override val vmc: KClass<QuizStartViewModel>
        get() = QuizStartViewModel::class

    override fun inflate(): Inflater<QuizFragmentStartBinding> = QuizFragmentStartBinding::inflate

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
