package com.space.quizapp.domain.usecase.user.save_user_token

import com.space.quizapp.domain.usecase.user.base.BaseUserTokenUseCase

class SaveUserTokenUseCaseImpl : BaseUserTokenUseCase(), SaveUserTokenUseCase {
    override suspend fun invoke(token: String) {
        repository.saveUserToken(token)
    }
}
