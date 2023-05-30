package com.space.quizapp.domain.usecase.user.save_user_token

import com.space.quizapp.domain.usecase.user.base.QuizBaseUserTokenUseCase

class QuizSaveUserTokenUseCaseImpl : QuizBaseUserTokenUseCase(), QuizSaveUserTokenUseCase {
    override suspend fun invoke(token: String) {
        repository.saveUserToken(token)
    }
}
