package com.space.main.presentation.ui.ui_start.vm

import com.space.common.base.viewmodel.BaseViewModel
import com.space.common.extensions.coroutines.executeAsync
import com.space.common.util.Constants.EMPTY_STRING
import com.space.common.util.CustomThrowable
import com.space.common.util.LiveDataDelegate
import com.space.main.domain.usecase.user.ReadUserTokenUseCase
import com.space.main.domain.usecase.user.SaveUserDataUseCase
import com.space.main.domain.usecase.user.ValidateUserUseCase
import com.space.main.presentation.model.user.UserUiModel
import com.space.main.presentation.model.user.mapper.user.UserUiMapper
import com.space.navigation_api.AppNavigator
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.withContext

class StartViewModel(
    private val saveUserDataUC: SaveUserDataUseCase,
    private val readUserTokenUC: ReadUserTokenUseCase,
    private val validateUserUC: ValidateUserUseCase,
    private val userMapper: UserUiMapper,
    private val appNavigator: AppNavigator
) : BaseViewModel() {
    val userValidState by LiveDataDelegate(false)
    fun validateUser(username: String) {
        executeAsync {
            val error = when (val validateUser = validateUserUC(
                username.replace(" ", EMPTY_STRING).replace("\n", EMPTY_STRING)
            )) {
                com.space.common.util.UserValidation.VALID -> {
                    userValidState.post(true)
                    null
                }

                else -> {
                    userValidState.post(false)
                    validateUser.message
                }
            }
            postError(error?.let { CustomThrowable(it) })
        }
    }

    fun saveUser(username: String) {
        executeAsync(IO) {
            saveUserDataUC(userMapper.toDomain(UserUiModel(userName = username)))
            appNavigator.navigateToHome()
        }
    }

    fun checkUserToken() {
        executeAsync(IO) {
            val token = readUserTokenUC()
            if (token != EMPTY_STRING) {
                withContext(Main) {
                    appNavigator.navigateToHome()
                }
            }
        }
    }
}
