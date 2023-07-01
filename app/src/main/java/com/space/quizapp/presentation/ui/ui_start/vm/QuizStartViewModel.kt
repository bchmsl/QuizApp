package com.space.quizapp.presentation.ui.ui_start.vm

import com.space.quizapp.common.extensions.coroutines.executeAsync
import com.space.quizapp.common.util.QuizConstants.EMPTY_STRING
import com.space.quizapp.common.util.QuizCustomThrowable
import com.space.quizapp.common.util.QuizLiveDataDelegate
import com.space.quizapp.common.util.QuizUserValidation
import com.space.quizapp.domain.usecase.user.QuizReadUserTokenUseCase
import com.space.quizapp.domain.usecase.user.QuizSaveUserDataUseCase
import com.space.quizapp.domain.usecase.user.ValidateUserUseCase
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.model.user.QuizUserUiModel
import com.space.quizapp.presentation.model.user.mapper.user.QuizUserUiMapper
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections
import kotlinx.coroutines.Dispatchers.IO

class QuizStartViewModel(
    private val saveUserDataUC: QuizSaveUserDataUseCase,
    private val readUserTokenUC: QuizReadUserTokenUseCase,
    private val validateUserUC: ValidateUserUseCase,
    private val userMapper: QuizUserUiMapper
) : QuizBaseViewModel() {
    val userValidState by QuizLiveDataDelegate(false)
    fun validateUser(username: String) {
        executeAsync {
            val error = when (val validateUser =
                validateUserUC(username)) {
                QuizUserValidation.VALID -> {
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
        }
        navigate(QuizFragmentDirections.HOME)
    }

    fun checkUserToken() {
        executeAsync(IO) {
            val token = readUserTokenUC()
            if (token != EMPTY_STRING) {
                navigate(QuizFragmentDirections.HOME)
            }
        }
    }
}
