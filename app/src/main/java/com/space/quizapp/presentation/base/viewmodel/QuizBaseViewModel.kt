package com.space.quizapp.presentation.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.quizapp.presentation.ui.navigation.QuizFragmentDirections
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class QuizBaseViewModel : ViewModel(), QuizErrorState {

    private val _navigationState = MutableStateFlow<QuizFragmentDirections?>(null)
    val navigationState get() = _navigationState.asStateFlow()

    fun navigate(directions: QuizFragmentDirections) {
        viewModelScope.launch {
            _navigationState.emit(directions)
        }
    }
}
