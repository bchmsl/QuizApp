package com.space.quizapp.domain.usecase.user.base

import com.space.quizapp.domain.repository.UserRepository
import org.koin.java.KoinJavaComponent.inject

abstract class BaseUserUseCase {
    protected val repository: UserRepository by inject(UserRepository::class.java)
}
