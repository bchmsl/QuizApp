package com.space.quizapp.presentation.common.model.mapper

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.domain.model.QuizUserDomainModel
import com.space.quizapp.presentation.common.model.QuizUserUiModel

class QuizUserUiDomainMapper : QuizModelMapper<QuizUserUiModel, QuizUserDomainModel> {
    override fun invoke(model: QuizUserUiModel): QuizUserDomainModel =
        QuizUserDomainModel(
            username = model.userName,
            gpa = model.gpa
        )
}
