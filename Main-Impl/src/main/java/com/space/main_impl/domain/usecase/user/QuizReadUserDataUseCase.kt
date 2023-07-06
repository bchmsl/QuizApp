package com.space.main_impl.domain.usecase.user

import com.space.common.base.usecase.QuizBaseUseCase
import com.space.common.util.QuizConstants.EMPTY_STRING
import com.space.main_impl.domain.model.user.QuizUserDomainModel
import com.space.main_impl.domain.repository.user.QuizUserDataRepository

class QuizReadUserDataUseCase(
    private val readUserTokenUC: QuizReadUserTokenUseCase,
    private val userDataRepository: QuizUserDataRepository
) : QuizBaseUseCase<Unit, QuizUserDomainModel>() {

    override suspend fun invoke(params: Unit?): QuizUserDomainModel {
        val userToken = readUserTokenUC()
        return when {
            userToken != EMPTY_STRING -> userDataRepository.retrieveUserByToken(userToken)
            else -> throw RuntimeException("User Token Not Found")
        }
    }
}
