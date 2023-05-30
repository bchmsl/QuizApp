package com.space.quizapp.domain.usecase.user.base

import com.space.quizapp.domain.repository.QuizUserTokenRepository
import org.koin.java.KoinJavaComponent

abstract class QuizBaseUserTokenUseCase {
    protected val repository: QuizUserTokenRepository by KoinJavaComponent.inject(
        QuizUserTokenRepository::class.java
    )

}
