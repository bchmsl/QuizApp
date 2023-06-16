package com.space.quizapp.presentation.ui.ui_start.vm

import com.space.quizapp.common.extensions.coroutines.executeAsync
import com.space.quizapp.common.util.QuizCustomThrowable
import com.space.quizapp.common.util.QuizValidateUser
import com.space.quizapp.data.local.datastore.QuizUserDataStoreManager
import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.model.user.QuizUserUiModel
import com.space.quizapp.presentation.model.user.mapper.user.user.QuizUserUiDomainMapper
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections
import kotlinx.coroutines.Dispatchers.IO

class QuizStartViewModel(
    private val saveUserDataUC: QuizBaseUseCase<QuizUserDomainModel, QuizValidateUser>,
    private val readUserTokenUC: QuizBaseUseCase<Unit, String>,

    private val userUiDomainMapper: QuizUserUiDomainMapper
) : QuizBaseViewModel() {

    fun saveUser(username: String) {
        executeAsync(IO) {
            val validateUser = saveUserDataUC(userUiDomainMapper(QuizUserUiModel(username)))
            val error = when (validateUser) {
                QuizValidateUser.VALID -> {
                    navigate(QuizFragmentDirections.HOME)
                    null
                }
                else -> validateUser.message
            }
            emitError(error?.let { QuizCustomThrowable(it) })
        }
    }

    fun checkUserToken() {
        executeAsync(IO) {
            val token = readUserTokenUC()
            if (token != QuizUserDataStoreManager.EMPTY_STRING) {
                navigate(QuizFragmentDirections.HOME)
            }
        }
    }
}
