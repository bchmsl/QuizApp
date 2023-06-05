package com.space.quizapp.presentation.model.user.mapper.subject

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.domain.model.user.QuizUserSubjectDomainModel
import com.space.quizapp.presentation.model.user.QuizUserSubjectUiModel

class QuizUserSubjectUiDomainMapper :
    QuizModelMapper<QuizUserSubjectUiModel, QuizUserSubjectDomainModel> {
    override fun invoke(model: QuizUserSubjectUiModel): QuizUserSubjectDomainModel {
        return QuizUserSubjectDomainModel(
            id = model.id,
            title = model.title,
            description = model.description,
            icon = model.icon,
            score = model.score,
            username = model.username
        )
    }
}
