package com.space.quizapp.domain.usecase.questions.base

import com.space.quizapp.domain.repository.QuizQuestionsRepository
import org.koin.java.KoinJavaComponent.inject

abstract class QuizBaseQuestionsUseCase {
    protected val repository: QuizQuestionsRepository by inject(QuizQuestionsRepository::class.java)
}
