package com.space.quizapp.common.extensions.utils

import androidx.viewbinding.ViewBinding
import com.space.quizapp.presentation.base.fragment.QuizBaseFragment
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel

fun <VB : ViewBinding, VM : QuizBaseViewModel> QuizBaseFragment<VB, VM>.withBinding(block: VB.() -> Unit) {
    with(binding) {
        this.block()
    }
}
