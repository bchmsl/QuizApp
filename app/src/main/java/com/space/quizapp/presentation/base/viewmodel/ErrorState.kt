package com.space.quizapp.presentation.base.viewmodel

import androidx.annotation.StringRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface ErrorState {
    private val _errorState: MutableStateFlow<Any?>
        get() = MutableStateFlow(null)
    val errorState get() = _errorState.asStateFlow()

    suspend fun setError(throwable: Any? = null) {
        _errorState.emit(throwable)
    }

    suspend fun setError(@StringRes error: Int? = null) {
        _errorState.emit(error)
    }
}
