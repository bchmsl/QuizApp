package com.space.quizapp.domain.usecase.user.read_user_token

import com.space.quizapp.domain.usecase.user.base.BaseUserTokenUseCase
import kotlinx.coroutines.flow.Flow

class ReadUserTokenUseCaseImpl : BaseUserTokenUseCase(), ReadUserTokenUseCase {
    override suspend fun invoke(): Flow<String> =
        repository.getUserToken()
}
