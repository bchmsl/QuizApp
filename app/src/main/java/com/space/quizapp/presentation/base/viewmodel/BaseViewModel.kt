package com.space.quizapp.presentation.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.quizapp.presentation.ui.navigation.FragmentDirections
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel(), ErrorState {

    private val _navigationState = MutableStateFlow<FragmentDirections?>(null)
    val navigationState get() = _navigationState.asStateFlow()

    fun navigate(directions: FragmentDirections) {
        viewModelScope.launch {
            _navigationState.emit(directions)
        }
    }
}
