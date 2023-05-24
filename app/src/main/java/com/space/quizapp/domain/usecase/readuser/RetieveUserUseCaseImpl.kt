package com.space.quizapp.domain.usecase.readuser

import com.space.quizapp.domain.model.UserDomainModel
import com.space.quizapp.domain.usecase.user.base.BaseUserUseCase
import kotlinx.coroutines.flow.Flow

class RetrieveUserUseCaseImpl : BaseUserUseCase(), RetrieveUserUseCase {
    override suspend fun invoke(username: String): Flow<UserDomainModel> =
        repository.retrieveUserInfo(username)
}
