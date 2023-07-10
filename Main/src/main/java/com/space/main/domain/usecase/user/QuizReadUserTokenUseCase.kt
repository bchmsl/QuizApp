package com.space.main.domain.usecase.user

import com.space.common.base.usecase.QuizBaseUseCase
import com.space.main.domain.repository.user.QuizUserTokenRepository

class QuizReadUserTokenUseCase(
    private val userTokenRepository: QuizUserTokenRepository
) : QuizBaseUseCase<Unit, String>() {
    override suspend fun invoke(params: Unit?): String {
        return userTokenRepository.retrieveUserToken()
    }
}
