package com.space.quizapp.common.extensions.utils

import com.space.quizapp.common.resource.QuizResource
import com.space.quizapp.common.util.QuizCustomThrowable


suspend fun <T> QuizResource<T>.onSuccess(block: suspend (T) -> Unit): QuizResource<T> {
    return when (this) {
        is QuizResource.Success -> {
            block(this.data)
            this
        }
        else -> this
    }
}

suspend fun <T> QuizResource<T>.onError(block: suspend (QuizCustomThrowable) -> Unit): QuizResource<T> {
    return when (this) {
        is QuizResource.Error -> {
            block(this.error)
            this
        }
        else -> this
    }
}
