package com.space.quizapp.presentation.ui.start.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.quizapp.common.util.ValidateUser
import com.space.quizapp.domain.usecase.readuser.RetrieveUserUseCase
import com.space.quizapp.domain.usecase.saveuser.SaveUserUseCase
import com.space.quizapp.presentation.common.model.UserUiModel
import com.space.quizapp.presentation.common.model.mapper.UserDomainUiMapper
import com.space.quizapp.presentation.common.model.mapper.UserUiDomainMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StartViewModel(
    private val saveUserUC: SaveUserUseCase,
    private val retrieveUserUC: RetrieveUserUseCase,
    private val userUiDomainMapper: UserUiDomainMapper,
    private val userDomainUiMapper: UserDomainUiMapper
) : ViewModel() {

    private val _usernameState = MutableStateFlow<ValidateUser?>(null)
    val usernameState get() = _usernameState.asStateFlow()

    fun saveUser(username: String) {
        viewModelScope.launch {
            _usernameState.emit(saveUserUC(userUiDomainMapper(UserUiModel(userName = username))))
        }
    }

    fun retrieveUserInfo(username: String) {
        viewModelScope.launch {
            retrieveUserUC(username)
        }
    }
}
