package com.space.quizapp.domain.usecase.user.save_user_data

import com.space.quizapp.common.util.QuizValidateUser
import com.space.quizapp.data.local.datastore.QuizUserDataStoreManager.Companion.EMPTY_STRING
import com.space.quizapp.domain.model.QuizUserDomainModel
import com.space.quizapp.domain.usecase.user.base.QuizBaseUserDataUseCase
import com.space.quizapp.domain.usecase.user.save_user_token.QuizSaveUserTokenUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class QuizSaveUserDataUseCaseImpl(
    private val saveUserTokenUseCase: QuizSaveUserTokenUseCase,
) : QuizBaseUserDataUseCase(), QuizSaveUserDataUseCase {
    override suspend fun invoke(userDomainModel: QuizUserDomainModel): Flow<QuizValidateUser> =
        flow {
            val username = userDomainModel.username
            val userNameValid: QuizValidateUser = QuizValidateUser.validate(username)
            if (userNameValid == QuizValidateUser.VALID) {
                if (userDomainModel.token == EMPTY_STRING) {
                    var userToken = repository.getUserTokenIfExists(username)
                    if (userToken == null) {
                        userToken = UUID.randomUUID().toString()
                        userDomainModel.token = userToken
                        repository.saveUserInfo(userDomainModel)
                    }
                    saveUserTokenUseCase(userToken)
                }
            }
            emit(userNameValid)
        }
}