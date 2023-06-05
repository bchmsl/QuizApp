package com.space.quizapp.data.local.database.model.user.mapper.subject

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.data.local.database.model.user.subject.QuizUserSubjectEntity
import com.space.quizapp.domain.model.user.QuizUserSubjectDomainModel

class QuizUserSubjectEntityDomainMapper :
    QuizModelMapper<QuizUserSubjectEntity, QuizUserSubjectDomainModel> {
    override fun invoke(model: QuizUserSubjectEntity): QuizUserSubjectDomainModel {
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
