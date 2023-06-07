package com.space.quizapp.domain.usecase.user.read_user_data

import com.space.quizapp.data.local.datastore.QuizUserDataStoreManager.Companion.EMPTY_STRING
import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.domain.usecase.user.read_user_token.QuizReadUserTokenUseCase

class RetrieveUserDataUseCaseImpl(
    private val readUserTokenUC: QuizReadUserTokenUseCase
) : QuizRetrieveUserDataUseCase() {
    override suspend fun invoke(params: Unit?): QuizUserDomainModel {
        val userToken = readUserTokenUC()
        if (userToken != EMPTY_STRING) {
            return repository.retrieveUserInfo(userToken)
        } else {
            throw RuntimeException("User Token Not Found")
        }
    }
}
