package com.space.common.util

import androidx.lifecycle.LiveData
import kotlin.reflect.KProperty

class QuizLiveDataDelegate<T>(initialValue: T) : LiveData<T>(initialValue) {
    operator fun getValue(
        thisRef: Any,
        property: KProperty<*>
    ): QuizLiveDataDelegate<T> {
        return this
    }

    fun post(value: T) {
        postValue(value)
    }
}