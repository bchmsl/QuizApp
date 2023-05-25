package com.space.quizapp.presentation.ui.start.vm

import androidx.lifecycle.ViewModel
import com.space.quizapp.common.extensions.executeAsync
import com.space.quizapp.common.util.ValidateUser
import com.space.quizapp.domain.usecase.readuser.RetrieveUserUseCase
import com.space.quizapp.domain.usecase.saveuser.SaveUserUseCase
import com.space.quizapp.presentation.common.model.UserUiModel
import com.space.quizapp.presentation.common.model.mapper.UserDomainUiMapper
import com.space.quizapp.presentation.common.model.mapper.UserUiDomainMapper
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class StartViewModel(
    private val saveUserUC: SaveUserUseCase,
    private val retrieveUserUC: RetrieveUserUseCase,
    private val userUiDomainMapper: UserUiDomainMapper,
    private val userDomainUiMapper: UserDomainUiMapper
) : ViewModel() {

    private val _usernameState = MutableSharedFlow<ValidateUser?>()
    val usernameState get() = _usernameState.asSharedFlow()

    fun saveUser(username: String) {
        executeAsync(IO) {
            saveUserUC(userUiDomainMapper(UserUiModel(username))).collect {
                _usernameState.emit(it)
            }
        }
    }

    fun retrieveUserInfo(username: String) {
        executeAsync(IO) {
            retrieveUserUC(username)
        }
    }
}
