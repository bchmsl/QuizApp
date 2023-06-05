package com.space.quizapp.data.local.database.model.user.mapper.subject

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.data.local.database.model.user.subject.QuizUserSubjectEntity
import com.space.quizapp.domain.model.user.QuizUserSubjectDomainModel

class QuizUserSubjectDomainEntityMapper :
    QuizModelMapper<QuizUserSubjectDomainModel, QuizUserSubjectEntity> {
    override fun invoke(model: QuizUserSubjectDomainModel): QuizUserSubjectEntity {
        return QuizUserSubjectEntity(
            id = model.id,
            title = model.title,
            description = model.description,
            icon = model.icon,
            username = model.username,
            score = model.score
        )
    }
}
