package com.space.quiz_impl.presentation.quiz.model

import com.space.common.mapper.QuizUiMapper
import com.space.quiz_impl.domain.model.QuizQuestionDomainModel

class QuizAnswerUiMapper :
    QuizUiMapper<QuizQuestionUiModel.QuizAnswerUiModel, QuizQuestionDomainModel.QuizAnswerDomainModel> {
    override fun toDomain(model: QuizQuestionUiModel.QuizAnswerUiModel):
            QuizQuestionDomainModel.QuizAnswerDomainModel {
        return with(model) {
            QuizQuestionDomainModel.QuizAnswerDomainModel(
                answerOption = answerOption,
                isCorrect = isCorrect,
                answerSelectedState = answerSelectedState
            )
        }
    }

    override fun toUi(model: QuizQuestionDomainModel.QuizAnswerDomainModel):
            QuizQuestionUiModel.QuizAnswerUiModel {
        return with(model) {
            QuizQuestionUiModel.QuizAnswerUiModel(
                answerOption = answerOption,
                isCorrect = isCorrect,
                answerSelectedState = answerSelectedState
            )
        }
    }
}
