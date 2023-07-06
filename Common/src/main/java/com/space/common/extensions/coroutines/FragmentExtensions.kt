package com.space.common.extensions.coroutines

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.space.common.util.QuizLiveDataDelegate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun Fragment.executeAsync(
    coroutineContext: CoroutineContext = Dispatchers.Main,
    lifecycleState: Lifecycle.State = Lifecycle.State.RESUMED,
    block: suspend CoroutineScope.() -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch(coroutineContext) {
        repeatOnLifecycle(lifecycleState) {
            this.block()
        }
    }
}

fun <T> Fragment.observeLiveData(
    liveData: QuizLiveDataDelegate<T>,
    block: (T) -> Unit
): QuizLiveDataDelegate<T> {
    liveData.observe(viewLifecycleOwner) {
        block(it)
    }
    return liveData
}

fun <T> Fragment.observeLiveDataNonNull(
    liveData: QuizLiveDataDelegate<T?>,
    block: (T) -> Unit
): QuizLiveDataDelegate<T?> {
    liveData.observe(viewLifecycleOwner) {
        it?.let {
            block(it)
        }
    }
    return liveData
}
