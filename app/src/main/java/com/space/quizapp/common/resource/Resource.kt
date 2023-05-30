package com.space.quizapp.common.resource

sealed class Resource<T>(val isLoading: Boolean = false) {
    class Success<T>(val data: T) : Resource<T>()
    class Loading<T> : Resource<T>(true)
    class Error<T>(val error: Throwable) : Resource<T>()
}
