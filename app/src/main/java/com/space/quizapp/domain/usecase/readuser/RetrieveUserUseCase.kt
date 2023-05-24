package com.space.quizapp.domain.usecase.readuser

import com.space.quizapp.domain.model.UserDomainModel
import kotlinx.coroutines.flow.Flow

interface RetrieveUserUseCase {
    suspend operator fun invoke(username: String): Flow<UserDomainModel>
}
