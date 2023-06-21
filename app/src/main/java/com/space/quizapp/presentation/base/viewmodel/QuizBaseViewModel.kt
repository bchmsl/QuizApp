package com.space.quizapp.presentation.base.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class QuizBaseViewModel : ViewModel() {

    private val _errorState: MutableStateFlow<Any?> = MutableStateFlow(null)
    val errorState get() = _errorState.asStateFlow()

    suspend fun setError(throwable: Any? = null) {
        _errorState.emit(throwable)
    }

    suspend fun setError(@StringRes error: Int? = null) {
        _errorState.emit(error)
    }

    private val _navigationState = MutableStateFlow<QuizFragmentDirections?>(null)
    val navigationState get() = _navigationState.asStateFlow()

    fun navigate(directions: QuizFragmentDirections) {
        viewModelScope.launch {
            _navigationState.emit(directions)
        }
    }
}
