package com.space.quizapp.domain.usecase.user.save_user_token

interface QuizSaveUserTokenUseCase {
    suspend operator fun invoke(token: String)
}
