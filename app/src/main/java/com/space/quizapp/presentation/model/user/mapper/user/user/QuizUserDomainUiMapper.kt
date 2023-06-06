package com.space.quizapp.presentation.model.user.mapper.user.user

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.presentation.model.user.QuizUserUiModel

class QuizUserDomainUiMapper : QuizModelMapper<QuizUserDomainModel, QuizUserUiModel> {
    override fun invoke(model: QuizUserDomainModel): QuizUserUiModel {
        return QuizUserUiModel(
            userName = model.username,
            gpa = model.gpa
        )
    }
}
