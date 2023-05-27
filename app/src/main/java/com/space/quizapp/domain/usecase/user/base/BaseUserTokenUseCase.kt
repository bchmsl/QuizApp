package com.space.quizapp.domain.usecase.user.base

import com.space.quizapp.domain.repository.UserTokenRepository
import org.koin.java.KoinJavaComponent

abstract class BaseUserTokenUseCase {
    protected val repository: UserTokenRepository by KoinJavaComponent.inject(UserTokenRepository::class.java)

}
