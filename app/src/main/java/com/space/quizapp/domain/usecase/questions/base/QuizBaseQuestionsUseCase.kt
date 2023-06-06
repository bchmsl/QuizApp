package com.space.quizapp.domain.usecase.questions.base

import com.space.quizapp.domain.repository.quiz.QuizRepository
import org.koin.java.KoinJavaComponent.inject

abstract class QuizBaseQuestionsUseCase {
    protected val repository: QuizRepository by inject(QuizRepository::class.java)
}
