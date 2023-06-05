package com.space.quizapp.common.resource

import com.space.quizapp.common.util.QuizCustomThrowable

sealed class QuizResource<T>(val isLoading: Boolean = false) {
    class Success<T>(val data: T) : QuizResource<T>()
    class Loading<T> : QuizResource<T>(true)
    class Error<T>(val error: QuizCustomThrowable) : QuizResource<T>()
}