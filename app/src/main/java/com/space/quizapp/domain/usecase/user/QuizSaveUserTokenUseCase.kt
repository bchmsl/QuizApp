package com.space.quizapp.domain.usecase.user

import com.space.common.base.usecase.QuizBaseUseCase
import com.space.quizapp.domain.repository.user.QuizUserTokenRepository

class QuizSaveUserTokenUseCase(
    private val userTokenRepository: QuizUserTokenRepository
) : com.space.common.base.usecase.QuizBaseUseCase<String, Unit>() {

    override suspend fun invoke(params: String?) {
        userTokenRepository.saveUserToken(params!!)
    }
}
