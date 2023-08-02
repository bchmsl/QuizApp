package com.space.common.resource

import com.space.common.util.CustomThrowable

sealed class Resource<T>(val isLoading: Boolean = false) {
    class Success<T>(val data: T) : Resource<T>()
    class Loading<T> : Resource<T>(true)
    class Error<T>(val error: CustomThrowable) : Resource<T>()
}
