package com.space.quizapp.common.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlin.reflect.KProperty

class QuizLiveDataDelegate<T>(initialValue: T) : LiveData<T>(initialValue) {
    operator fun getValue(
        thisRef: Any,
        property: KProperty<*>
    ): QuizLiveDataDelegate<T> {
        return this
    }

    internal fun addValue(value: T) {
        postValue(value)
    }
}

fun <T> ViewModel.postValue(
    livedata: QuizLiveDataDelegate<T?>,
    value: () -> T?
) {

    livedata.addValue(value())
}

suspend fun <T> ViewModel.postValueAsync(
    livedata: QuizLiveDataDelegate<T?>,
    value: suspend () -> T?
) {
    livedata.addValue(value())
}

fun <T> ViewModel.postValueNonNull(
    livedata: QuizLiveDataDelegate<T>,
    value: () -> T
) {
    livedata.addValue(value())
}

suspend fun <T> ViewModel.postValueAsyncNonNull(
    livedata: QuizLiveDataDelegate<T>,
    value: suspend () -> T
) {
    livedata.addValue(value())
}

