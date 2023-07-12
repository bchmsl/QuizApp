package com.space.common.extensions.coroutines

import androidx.fragment.app.Fragment
import com.space.common.util.LiveDataDelegate

fun <T> Fragment.observeLiveData(
    liveData: LiveDataDelegate<T>,
    block: (T) -> Unit
): LiveDataDelegate<T> {
    liveData.observe(viewLifecycleOwner) {
        block(it)
    }
    return liveData
}

fun <T> Fragment.observeLiveDataNonNull(
    liveData: LiveDataDelegate<T?>,
    block: (T) -> Unit
): LiveDataDelegate<T?> {
    liveData.observe(viewLifecycleOwner) {
        it?.let {
            block(it)
        }
    }
    return liveData
}
