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
    private val saveUserTokenUseCase: QuizSaveUserTokenUseCase
) : QuizBaseUserDataUseCase(), QuizSaveUserDataUseCase {
    override suspend fun invoke(userDomainModel: QuizUserDomainModel): Flow<QuizValidateUser> =
        flow {
            val userName = userDomainModel.username
            val userNameValid: QuizValidateUser = QuizValidateUser.validate(userName)
            if (userNameValid == QuizValidateUser.VALID) {
                if (userDomainModel.token == EMPTY_STRING) {
                    val userToken = UUID.randomUUID().toString()
                    userDomainModel.token = userToken
                    saveUserTokenUseCase(userToken)
                }
                repository.saveUserInfo(userDomainModel)
            }
            emit(userNameValid)
    }
}
