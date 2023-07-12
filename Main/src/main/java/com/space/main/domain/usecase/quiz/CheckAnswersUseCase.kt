package com.space.main.domain.usecase.quiz

import com.space.common.model.question.domain.QuestionDomainModel
import com.space.main.domain.repository.quiz.QuestionsRepository
import com.space.quiz_api.GetNextQuestion

class GetNextQuestionUseCase(
    private val repository: QuestionsRepository
) : GetNextQuestion {
    override suspend fun invoke(subjectTitle: String): QuestionDomainModel? {
        return repository.getNextQuestion(subjectTitle)
    }
}