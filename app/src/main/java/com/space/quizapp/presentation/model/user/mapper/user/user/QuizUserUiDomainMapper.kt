package com.space.quizapp.presentation.model.user.mapper.user.user

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.presentation.model.user.QuizUserUiModel

class QuizUserUiDomainMapper : QuizModelMapper<QuizUserUiModel, QuizUserDomainModel> {
    override fun invoke(model: QuizUserUiModel): QuizUserDomainModel {
        return QuizUserDomainModel(
            username = model.userName,
            gpa = model.gpa
        )
    }
}
