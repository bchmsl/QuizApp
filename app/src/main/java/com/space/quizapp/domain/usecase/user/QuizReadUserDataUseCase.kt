package com.space.quizapp.domain.usecase.user.read_user_data

import com.space.quizapp.data.local.datastore.QuizUserDataStoreManager.Companion.EMPTY_STRING
import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.domain.repository.user.QuizUserDataRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase

class QuizReadUserDataUseCase(
    private val readUserTokenUC: QuizBaseUseCase<Unit, String>,
    private val repository: QuizUserDataRepository
) : QuizBaseUseCase<Unit, QuizUserDomainModel>() {

    override suspend fun invoke(params: Unit?): QuizUserDomainModel {
        val userToken = readUserTokenUC()
        if (userToken != EMPTY_STRING) {
            return repository.retrieveUserInfo(userToken)
        } else {
            throw RuntimeException("User Token Not Found")
        }
    }
}