package com.space.common.util

import androidx.lifecycle.LiveData
import kotlin.reflect.KProperty

class LiveDataDelegate<T>(initialValue: T) : LiveData<T>(initialValue) {
    operator fun getValue(
        thisRef: Any,
        property: KProperty<*>
    ): LiveDataDelegate<T> {
        return this
    }

    fun post(value: T) {
        postValue(value)
    }
}