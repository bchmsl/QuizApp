package com.space.quizapp.presentation.common.model.mapper

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.domain.model.QuizSubjectDomainModel
import com.space.quizapp.presentation.common.model.QuizSubjectUiModel

class QuizSubjectUiDomainMapper : QuizModelMapper<QuizSubjectUiModel, QuizSubjectDomainModel> {
    override fun invoke(model: QuizSubjectUiModel): QuizSubjectDomainModel =
        QuizSubjectDomainModel(
            id = model.id,
            username = model.userName,
            title = model.title,
            description = model.description,
            icon = model.icon,
            score = model.score
        )
}
