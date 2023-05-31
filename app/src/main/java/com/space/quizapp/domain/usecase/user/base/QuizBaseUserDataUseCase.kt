package com.space.quizapp.domain.usecase.user.base

import com.space.quizapp.domain.repository.user.QuizUserDataRepository
import org.koin.java.KoinJavaComponent.inject

abstract class QuizBaseUserDataUseCase {
    protected val repository: QuizUserDataRepository by inject(QuizUserDataRepository::class.java)
}
