package com.space.quizapp.presentation.model.quiz.mapper

import com.space.quizapp.common.mapper.QuizUiMapper
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.presentation.model.quiz.QuizQuestionUiModel

class QuizAnswerUiMapper :
    QuizUiMapper<QuizQuestionUiModel.QuizAnswerUiModel, QuizQuestionDomainModel.QuizAnswerDomainModel> {
    override fun toDomain(model: QuizQuestionUiModel.QuizAnswerUiModel): QuizQuestionDomainModel.QuizAnswerDomainModel {
        return with(model) {
            QuizQuestionDomainModel.QuizAnswerDomainModel(
                answerOption = answerOption,
                isCorrect = isCorrect
            )
        }
    }

    override fun toUi(model: QuizQuestionDomainModel.QuizAnswerDomainModel): QuizQuestionUiModel.QuizAnswerUiModel {
        return with(model) {
            QuizQuestionUiModel.QuizAnswerUiModel(
                answerOption = answerOption,
                isCorrect = isCorrect
            )
        }
    }
}
