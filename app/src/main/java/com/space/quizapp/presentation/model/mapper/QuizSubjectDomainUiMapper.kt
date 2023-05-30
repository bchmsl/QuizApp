package com.space.quizapp.presentation.model.mapper

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.domain.model.QuizSubjectDomainModel
import com.space.quizapp.presentation.model.QuizSubjectUiModel

class QuizSubjectDomainUiMapper : QuizModelMapper<QuizSubjectDomainModel, QuizSubjectUiModel> {
    override fun invoke(model: QuizSubjectDomainModel): QuizSubjectUiModel =
        QuizSubjectUiModel(
            id = model.id,
            userName = model.username,
            title = model.title,
            description = model.description,
            icon = model.icon,
            score = model.score
        )
}
