package com.space.quizapp.domain.usecase.user.read_user_data

import com.space.quizapp.data.local.datastore.QuizUserDataStoreManager.Companion.EMPTY_STRING
import com.space.quizapp.domain.model.QuizUserDomainModel
import com.space.quizapp.domain.usecase.user.base.QuizBaseUserDataUseCase
import com.space.quizapp.domain.usecase.user.read_user_token.QuizReadUserTokenUseCase
import kotlinx.coroutines.flow.Flow

class RetrieveUserDataDataUseCaseImpl(
    private val readUserTokenUC: QuizReadUserTokenUseCase
) : QuizBaseUserDataUseCase(), QuizRetrieveUserDataUseCase {
    override suspend fun invoke(): Flow<QuizUserDomainModel> {
        var userToken = EMPTY_STRING
        readUserTokenUC().collect { token ->
            if (token != EMPTY_STRING) {
                userToken = token
            } else {
                throw RuntimeException("User token not found!")
            }
        }
        return repository.retrieveUserInfo(userToken)
    }
}
