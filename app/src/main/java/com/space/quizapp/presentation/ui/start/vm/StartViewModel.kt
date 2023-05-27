package com.space.quizapp.presentation.ui.start.vm

import com.space.quizapp.common.extensions.executeAsync
import com.space.quizapp.common.util.ValidateUser
import com.space.quizapp.data.local.datastore.UserDataStoreManager
import com.space.quizapp.domain.usecase.user.read_user_token.ReadUserTokenUseCase
import com.space.quizapp.domain.usecase.user.save_user_data.SaveUserDataUseCase
import com.space.quizapp.presentation.base.viewmodel.BaseViewModel
import com.space.quizapp.presentation.common.model.UserUiModel
import com.space.quizapp.presentation.common.model.mapper.UserUiDomainMapper
import com.space.quizapp.presentation.ui.navigation.FragmentDirections.START_TO_HOME
import kotlinx.coroutines.Dispatchers.IO

class StartViewModel(
    private val saveUserDataUC: SaveUserDataUseCase,
    private val readUserTokenUC: ReadUserTokenUseCase,
    private val userUiDomainMapper: UserUiDomainMapper
) : BaseViewModel() {

    fun saveUser(username: String) {
        executeAsync(IO) {
            saveUserDataUC(userUiDomainMapper(UserUiModel(username))).collect { validateUser ->
                val error = when (validateUser) {
                    ValidateUser.VALID -> {
                        navigate(START_TO_HOME)
                        null
                    }
                    else -> validateUser.message
                }
                setError(error)
            }
        }
    }

    fun checkUserToken() {
        executeAsync(IO) {
            readUserTokenUC().collect { token ->
                if (token != UserDataStoreManager.EMPTY_STRING) {
                    navigate(START_TO_HOME)
                }
            }
        }
    }
}
