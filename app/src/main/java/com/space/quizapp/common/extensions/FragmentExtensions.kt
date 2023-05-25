package com.space.quizapp.common.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import com.space.quizapp.presentation.base.fragment.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun <VB : ViewBinding, VM : ViewModel> BaseFragment<VB, VM>.withBinding(block: VB.() -> Unit) {
    with(binding) {
        this.block()
    }
}

fun <T : Any?> Fragment.collectAsync(
    flow: Flow<T>,
    lifecycleState: Lifecycle.State = Lifecycle.State.RESUMED,
    block: (T) -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        flow.flowWithLifecycle(this@collectAsync.lifecycle, lifecycleState).collect {
            block(it)
        }
    }
}

fun Fragment.executeAsync(
    coroutineContext: CoroutineContext = Dispatchers.Unconfined,
    lifecycleState: Lifecycle.State = Lifecycle.State.RESUMED,
    block: suspend CoroutineScope.() -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch(coroutineContext) {
        repeatOnLifecycle(lifecycleState) {
            this.block()
        }
    }
}
