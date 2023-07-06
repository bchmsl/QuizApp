package com.space.quizapp.domain.usecase.user

import com.space.quizapp.domain.repository.user.QuizUserTokenRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase

class QuizReadUserTokenUseCase(
    private val userTokenRepository: QuizUserTokenRepository
) : QuizBaseUseCase<Unit, String>() {
    override suspend fun invoke(params: Unit?): String {
        return userTokenRepository.retrieveUserToken()
    }
}
