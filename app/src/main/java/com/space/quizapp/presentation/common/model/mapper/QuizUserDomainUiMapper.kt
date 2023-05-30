package com.space.quizapp.presentation.common.model.mapper

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.domain.model.QuizUserDomainModel
import com.space.quizapp.presentation.common.model.QuizUserUiModel

class QuizUserDomainUiMapper : QuizModelMapper<QuizUserDomainModel, QuizUserUiModel> {
    override fun invoke(model: QuizUserDomainModel): QuizUserUiModel =
        QuizUserUiModel(
            userName = model.username,
            gpa = model.gpa,
        )

}
