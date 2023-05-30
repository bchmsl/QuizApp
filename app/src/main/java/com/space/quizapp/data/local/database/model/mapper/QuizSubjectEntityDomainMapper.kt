package com.space.quizapp.data.local.database.model.mapper

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.data.local.database.model.QuizSubjectEntity
import com.space.quizapp.domain.model.QuizSubjectDomainModel

class QuizSubjectEntityDomainMapper :
    QuizModelMapper<QuizSubjectEntity, QuizSubjectDomainModel> {
    override fun invoke(model: QuizSubjectEntity): QuizSubjectDomainModel =
        QuizSubjectDomainModel(
            id = model.id,
            username = model.username,
            title = model.title,
            description = model.description,
            icon = model.icon,
            score = model.score,
        )
}
