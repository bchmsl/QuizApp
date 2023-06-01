package com.space.quizapp.presentation.base.viewmodel

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.quizapp.common.util.QuizCustomThrowable
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class QuizBaseViewModel : ViewModel() {

    private val _errorState: MutableStateFlow<QuizCustomThrowable?> = MutableStateFlow(null)
    val errorState get() = _errorState.asStateFlow()

    suspend fun emitError(throwable: QuizCustomThrowable? = null) {
        _errorState.emit(throwable)
    }

    private val _navigationState = MutableStateFlow<QuizFragmentDirections?>(null)
    val navigationState get() = _navigationState.asStateFlow()

    fun navigate(directions: QuizFragmentDirections, vararg arguments: Any) {
        viewModelScope.launch {
            arguments.forEach { argument ->
                when (argument) {
                    is String -> directions.addArgument(argument)
                    is Parcelable -> directions.addArgument(argument)
                }
            }
            _navigationState.emit(directions)
        }
    }
}
