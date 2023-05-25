package com.space.quizapp.presentation.ui.start.vm

import com.space.quizapp.common.extensions.executeAsync
import com.space.quizapp.common.util.ValidateUser
import com.space.quizapp.domain.usecase.readuser.RetrieveUserUseCase
import com.space.quizapp.domain.usecase.saveuser.SaveUserUseCase
import com.space.quizapp.presentation.base.viewmodel.BaseViewModel
import com.space.quizapp.presentation.common.model.UserUiModel
import com.space.quizapp.presentation.common.model.mapper.UserDomainUiMapper
import com.space.quizapp.presentation.common.model.mapper.UserUiDomainMapper
import com.space.quizapp.presentation.ui.navigation.FragmentDirections
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StartViewModel(
    private val saveUserUC: SaveUserUseCase,
    private val retrieveUserUC: RetrieveUserUseCase,
    private val userUiDomainMapper: UserUiDomainMapper,
    private val userDomainUiMapper: UserDomainUiMapper
) : BaseViewModel() {

    private val _usernameErrorState = MutableStateFlow<ValidateUser?>(null)
    val usernameErrorState get() = _usernameErrorState.asStateFlow()


    fun saveUser(username: String) {
        executeAsync(IO) {
            saveUserUC(userUiDomainMapper(UserUiModel(username))).collect { validateUser ->
                _usernameErrorState.emit(
                    when (validateUser) {
                        ValidateUser.VALID -> {
                            navigate(FragmentDirections.START_TO_HOME)
                            null
                        }
                        else -> validateUser
                    }
                )
            }
        }
    }

    fun retrieveUserInfo(username: String) {
        executeAsync(IO) {
            retrieveUserUC(username)
        }
    }
}
