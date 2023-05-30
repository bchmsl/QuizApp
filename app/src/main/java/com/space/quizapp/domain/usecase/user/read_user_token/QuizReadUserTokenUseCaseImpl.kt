package com.space.quizapp.domain.usecase.user.read_user_token

import com.space.quizapp.domain.usecase.user.base.QuizBaseUserTokenUseCase
import kotlinx.coroutines.flow.Flow

class QuizReadUserTokenUseCaseImpl : QuizBaseUserTokenUseCase(), QuizReadUserTokenUseCase {
    override suspend fun invoke(): Flow<String> =
        repository.getUserToken()
}
