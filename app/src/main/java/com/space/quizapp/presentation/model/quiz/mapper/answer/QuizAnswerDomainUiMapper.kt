package com.space.quizapp.presentation.model.quiz.mapper.answer

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.presentation.model.quiz.QuizAnswerUiModel

class QuizAnswerDomainUiMapper : QuizModelMapper<QuizAnswerDomainModel, QuizAnswerUiModel> {
    override fun invoke(model: QuizAnswerDomainModel): QuizAnswerUiModel {
        return QuizAnswerUiModel(
            answerOption = model.answerOption,
            selectedState = model.selectedState
        )
    }
}
