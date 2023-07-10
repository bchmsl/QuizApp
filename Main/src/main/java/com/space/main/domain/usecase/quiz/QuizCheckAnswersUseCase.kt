package com.space.main.domain.usecase.quiz

import com.space.common.model.question.domain.QuizQuestionDomainModel
import com.space.main.domain.repository.quiz.QuizQuestionsRepository
import com.space.quiz_api.GetNextQuestion

class GetNextQuestionUseCase(
    private val repository: QuizQuestionsRepository
) : GetNextQuestion {
    override suspend fun invoke(subjectTitle: String): QuizQuestionDomainModel? {
        return repository.getNextQuestion(subjectTitle)
    }
}