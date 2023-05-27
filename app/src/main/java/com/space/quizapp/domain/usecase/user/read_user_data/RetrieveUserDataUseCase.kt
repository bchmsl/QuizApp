package com.space.quizapp.domain.usecase.user.read_user_data

import com.space.quizapp.domain.model.UserDomainModel
import kotlinx.coroutines.flow.Flow

interface RetrieveUserDataUseCase {
    suspend operator fun invoke(): Flow<UserDomainModel>
}
