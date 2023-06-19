package com.space.quizapp.domain.usecase.user.read_user_data

import com.space.quizapp.data.local.datastore.QuizUserDataStoreManager.Companion.EMPTY_STRING
import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.domain.repository.user.QuizUserDataRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import com.space.quizapp.domain.usecase.user.QuizReadUserTokenUseCase

class QuizReadUserDataUseCase(
    private val readUserToken: QuizReadUserTokenUseCase,
    private val repository: QuizUserDataRepository
) : QuizBaseUseCase<Unit, QuizUserDomainModel>() {

    override suspend fun invoke(params: Unit?): QuizUserDomainModel {
        val userToken = readUserToken()
        return when {
            userToken != EMPTY_STRING -> repository.retrieveUser(userToken)
            else -> throw RuntimeException("User Token Not Found")
        }
    }
}
