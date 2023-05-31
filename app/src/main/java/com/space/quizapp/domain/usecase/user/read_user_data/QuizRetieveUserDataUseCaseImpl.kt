package com.space.quizapp.domain.usecase.user.read_user_data

import com.space.quizapp.data.local.datastore.QuizUserDataStoreManager.Companion.EMPTY_STRING
import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.domain.usecase.user.base.QuizBaseUserDataUseCase
import com.space.quizapp.domain.usecase.user.read_user_token.QuizReadUserTokenUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class RetrieveUserDataDataUseCaseImpl(
    private val readUserTokenUC: QuizReadUserTokenUseCase
) : QuizBaseUserDataUseCase(), QuizRetrieveUserDataUseCase {
    override suspend fun invoke(): Flow<QuizUserDomainModel> {
        val userToken = readUserTokenUC().first()
        if (userToken != EMPTY_STRING) {
            return repository.retrieveUserInfo(userToken)
        } else {
            throw RuntimeException("User token not found!")
        }
    }
}
