package com.space.quizapp.domain.usecase.questions.get_questions

import com.space.quizapp.domain.usecase.questions.base.QuizBaseQuestionsUseCase

class QuizQuestionsUseCaseImpl : QuizBaseQuestionsUseCase(), QuizQuestionsUseCase {
    override suspend fun invoke() = repository.retrieveQuestions()
}