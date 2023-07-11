package com.space.common.model.question.model

import com.space.common.mapper.UiMapper
import com.space.common.model.question.domain.QuestionDomainModel

class AnswerUiMapper :
    UiMapper<QuestionUiModel.QuizAnswerUiModel, QuestionDomainModel.AnswerDomainModel> {
    override fun toDomain(model: QuestionUiModel.QuizAnswerUiModel):
            QuestionDomainModel.AnswerDomainModel {
        return with(model) {
            QuestionDomainModel.AnswerDomainModel(
                answerOption = answerOption,
                isCorrect = isCorrect,
                answerSelectedState = answerSelectedState
            )
        }
    }

    override fun toUi(model: QuestionDomainModel.AnswerDomainModel):
            QuestionUiModel.QuizAnswerUiModel {
        return with(model) {
            QuestionUiModel.QuizAnswerUiModel(
                answerOption = answerOption,
                isCorrect = isCorrect,
                answerSelectedState = answerSelectedState
            )
        }
    }
}
