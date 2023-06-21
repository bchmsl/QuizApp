package com.space.quizapp.data.local.database.model.mapper

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.data.local.database.model.QuizSubjectEntity
import com.space.quizapp.domain.model.QuizSubjectDomainModel

class QuizSubjectDomainEntityMapper :
    QuizModelMapper<QuizSubjectDomainModel, QuizSubjectEntity> {
    override fun invoke(model: QuizSubjectDomainModel): QuizSubjectEntity =
        QuizSubjectEntity(
            id = model.id,
            username = model.username,
            title = model.title,
            description = model.description,
            icon = model.icon,
            score = model.score,
        )
}
