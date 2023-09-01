package com.space.common.extensions.utils

import com.space.common.resource.Resource
import com.space.common.util.CustomThrowable

suspend fun <T> Resource<T>.onSuccess(block: suspend (T) -> Unit): Resource<T> {
    return when (this) {
        is Resource.Success -> {
            block(this.data)
            this
        }

        else -> this
    }
}

suspend fun <T> Resource<T>.onError(block: suspend (CustomThrowable) -> Unit): Resource<T> {
    return when (this) {
        is Resource.Error -> {
            block(this.error)
            this
        }

        else -> this
    }
}
