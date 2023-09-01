package com.space.main.domain.usecase.questions

import com.space.common.model.question.domain.QuestionDomainModel
import com.space.main.domain.repository.quiz.QuestionsRepository
import com.space.quiz_api.UpdateQuestion

class UpdateQuestionUseCase(private val questionsRepository: QuestionsRepository) :
    UpdateQuestion {
    override suspend fun invoke(questionDomainModel: QuestionDomainModel) {
        questionsRepository.updateQuestion(questionDomainModel)
    }
}