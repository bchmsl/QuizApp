package com.space.quizapp.domain.usecase.user.read_user_token

import kotlinx.coroutines.flow.Flow


interface QuizReadUserTokenUseCase {
    suspend operator fun invoke(): Flow<String>
}
