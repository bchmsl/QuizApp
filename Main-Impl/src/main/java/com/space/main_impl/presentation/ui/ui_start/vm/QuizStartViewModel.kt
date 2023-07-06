package com.space.main_impl.presentation.ui.ui_start.vm

import com.space.common.base.viewmodel.QuizBaseViewModel
import com.space.common.extensions.coroutines.executeAsync
import com.space.common.util.QuizConstants.EMPTY_STRING
import com.space.common.util.QuizCustomThrowable
import com.space.common.util.QuizLiveDataDelegate
import com.space.main_impl.domain.usecase.user.QuizReadUserTokenUseCase
import com.space.main_impl.domain.usecase.user.QuizSaveUserDataUseCase
import com.space.main_impl.domain.usecase.user.ValidateUserUseCase
import com.space.main_impl.presentation.model.user.QuizUserUiModel
import com.space.main_impl.presentation.model.user.mapper.user.QuizUserUiMapper
import com.space.navigation_api.AppNavigator
import kotlinx.coroutines.Dispatchers.IO

class QuizStartViewModel(
    private val saveUserDataUC: QuizSaveUserDataUseCase,
    private val readUserTokenUC: QuizReadUserTokenUseCase,
    private val validateUserUC: ValidateUserUseCase,
    private val userMapper: QuizUserUiMapper,
    private val appNavigator: AppNavigator
) : QuizBaseViewModel() {
    val userValidState by QuizLiveDataDelegate(false)
    fun validateUser(username: String) {
        executeAsync {
            val error = when (val validateUser = validateUserUC(
                username.replace(" ", EMPTY_STRING).replace("\n", EMPTY_STRING)
            )) {
                com.space.common.util.QuizUserValidation.VALID -> {
                    userValidState.post(true)
                    null
                }

                else -> {
                    userValidState.post(false)
                    validateUser.message
                }
            }
            postError(error?.let { QuizCustomThrowable(it) })
        }
    }

    fun saveUser(username: String) {
        executeAsync(IO) {
            saveUserDataUC(userMapper.toDomain(QuizUserUiModel(userName = username)))
            appNavigator.navigateToHome()
        }
    }

    fun checkUserToken() {
        executeAsync(IO) {
            val token = readUserTokenUC()
            if (token != EMPTY_STRING) {
                appNavigator.navigateToHome()
            }
        }
    }
}
