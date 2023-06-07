package com.space.quizapp.presentation.model.quiz.mapper.answer

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.presentation.model.quiz.QuizQuestionUiModel

class QuizAnswerUiDomainMapper :
    QuizModelMapper<QuizQuestionUiModel.QuizAnswerUiModel, QuizQuestionDomainModel.QuizAnswerDomainModel> {
    override fun invoke(model: QuizQuestionUiModel.QuizAnswerUiModel): QuizQuestionDomainModel.QuizAnswerDomainModel {
        return QuizQuestionDomainModel.QuizAnswerDomainModel(
            answerOption = model.answerOption,
            selectedState = model.selectedState
        )
    }
}
