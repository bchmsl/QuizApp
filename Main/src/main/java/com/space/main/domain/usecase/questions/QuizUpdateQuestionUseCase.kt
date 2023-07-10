package com.space.main.domain.usecase.questions

import com.space.common.model.question.domain.QuizQuestionDomainModel
import com.space.main.domain.repository.quiz.QuizQuestionsRepository
import com.space.quiz_api.UpdateQuestion

class QuizUpdateQuestionUseCase(private val questionsRepository: QuizQuestionsRepository) :
    UpdateQuestion {
    override suspend fun invoke(questionDomainModel: QuizQuestionDomainModel) {
        questionsRepository.updateQuestion(questionDomainModel)
    }
}