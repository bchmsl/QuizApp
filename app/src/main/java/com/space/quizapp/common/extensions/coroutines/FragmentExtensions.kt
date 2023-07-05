package com.space.quizapp.common.extensions.coroutines

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.space.quizapp.common.util.QuizLiveDataDelegate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

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
