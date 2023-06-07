package com.space.quizapp.presentation.model.quiz.mapper.answer

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.presentation.model.quiz.QuizQuestionUiModel

class QuizAnswerDomainUiMapper :
    QuizModelMapper<QuizQuestionDomainModel.QuizAnswerDomainModel, QuizQuestionUiModel.QuizAnswerUiModel> {
    override fun invoke(model: QuizQuestionDomainModel.QuizAnswerDomainModel): QuizQuestionUiModel.QuizAnswerUiModel {
        return QuizQuestionUiModel.QuizAnswerUiModel(
            answerOption = model.answerOption,
            selectedState = model.selectedState
        )
    }
}
