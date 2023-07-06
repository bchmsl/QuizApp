package com.space.quizapp.domain.usecase.user

import com.space.common.base.usecase.QuizBaseUseCase
import com.space.quizapp.domain.repository.user.QuizUserTokenRepository

class QuizReadUserTokenUseCase(
    private val userTokenRepository: QuizUserTokenRepository
) : com.space.common.base.usecase.QuizBaseUseCase<Unit, String>() {
    override suspend fun invoke(params: Unit?): String {
        return userTokenRepository.retrieveUserToken()
    }
}
