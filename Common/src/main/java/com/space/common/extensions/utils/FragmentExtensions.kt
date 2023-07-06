package com.space.common.extensions.utils

import androidx.viewbinding.ViewBinding
import com.space.common.base.fragment.QuizBaseFragment
import com.space.common.base.viewmodel.QuizBaseViewModel

fun <VB : ViewBinding, VM : QuizBaseViewModel> QuizBaseFragment<VB, VM>.withBinding(block: VB.() -> Unit) {
    with(binding) {
        this.block()
    }
}
