package com.space.quizapp.domain.usecase.user

import com.space.quizapp.domain.repository.user.QuizUserTokenRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase

class QuizSaveUserTokenUseCase(
    private val repository: QuizUserTokenRepository
) : QuizBaseUseCase<String, Unit>() {

    override suspend fun invoke(token: String?) {
        repository.saveUserToken(token!!)
    }
}
