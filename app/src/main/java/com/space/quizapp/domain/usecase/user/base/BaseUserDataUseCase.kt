package com.space.quizapp.domain.usecase.user.base

import com.space.quizapp.domain.repository.UserDataRepository
import org.koin.java.KoinJavaComponent.inject

abstract class BaseUserDataUseCase {
    protected val repository: UserDataRepository by inject(UserDataRepository::class.java)
}
