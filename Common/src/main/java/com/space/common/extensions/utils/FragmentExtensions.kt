package com.space.common.extensions.utils

import androidx.viewbinding.ViewBinding
import com.space.common.base.fragment.BaseFragment
import com.space.common.base.viewmodel.BaseViewModel

fun <VB : ViewBinding, VM : BaseViewModel> BaseFragment<VB, VM>.withBinding(block: VB.() -> Unit) {
    with(binding) {
        this.block()
    }
}
