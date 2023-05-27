package com.space.quizapp.domain.usecase.user.save_user_token

interface SaveUserTokenUseCase {
    suspend operator fun invoke(token: String)
}
