package com.space.quizapp.domain.usecase.user.read_user_data

import com.space.quizapp.domain.model.QuizUserDomainModel
import kotlinx.coroutines.flow.Flow

interface QuizRetrieveUserDataUseCase {
    suspend operator fun invoke(): Flow<QuizUserDomainModel>
}
