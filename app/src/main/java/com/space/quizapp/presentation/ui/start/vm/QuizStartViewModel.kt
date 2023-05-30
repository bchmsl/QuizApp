package com.space.quizapp.presentation.ui.start.vm

import com.space.quizapp.common.extensions.executeAsync
import com.space.quizapp.common.util.QuizValidateUser
import com.space.quizapp.data.local.datastore.QuizUserDataStoreManager
import com.space.quizapp.domain.usecase.user.read_user_token.QuizReadUserTokenUseCase
import com.space.quizapp.domain.usecase.user.save_user_data.QuizSaveUserDataUseCase
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.common.model.QuizUserUiModel
import com.space.quizapp.presentation.common.model.mapper.QuizUserUiDomainMapper
import com.space.quizapp.presentation.ui.navigation.QuizFragmentDirections.START_TO_HOME
import kotlinx.coroutines.Dispatchers.IO

class QuizStartViewModel(
    private val saveUserDataUC: QuizSaveUserDataUseCase,
    private val readUserTokenUC: QuizReadUserTokenUseCase,
    private val userUiDomainMapper: QuizUserUiDomainMapper
) : QuizBaseViewModel() {

    fun saveUser(username: String) {
        executeAsync(IO) {
            saveUserDataUC(userUiDomainMapper(QuizUserUiModel(username))).collect { validateUser ->
                val error = when (validateUser) {
                    QuizValidateUser.VALID -> {
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
                if (token != QuizUserDataStoreManager.EMPTY_STRING) {
                    navigate(START_TO_HOME)
                }
            }
        }
    }
}
