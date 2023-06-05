package com.space.quizapp.presentation.model.quiz.mapper.answer

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.domain.model.quiz.QuizAnswerDomainModel
import com.space.quizapp.presentation.model.quiz.QuizAnswerUiModel

class QuizAnswerUiDomainMapper : QuizModelMapper<QuizAnswerUiModel, QuizAnswerDomainModel> {
    override fun invoke(model: QuizAnswerUiModel): QuizAnswerDomainModel {
        return QuizAnswerDomainModel(
            answerOption = model.answerOption,
            selectedState = model.selectedState
        )
    }
}
