package com.space.quizapp.data.local.database.model.user.mapper.user

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.data.local.database.model.user.mapper.subject.QuizUserSubjectEntityDomainMapper
import com.space.quizapp.domain.model.user.QuizUserDomainModel

class QuizUserEntityDomainMapper(
    private val quizUserSubjectEntityDomainMapper: QuizUserSubjectEntityDomainMapper
) : QuizModelMapper<QuizUserWithSubjectsEntity, QuizUserDomainModel> {
    override fun invoke(model: QuizUserWithSubjectsEntity): QuizUserDomainModel {
        return QuizUserDomainModel(
            username = model.username,
            token = model.token,
            gpa = model.gpa,
            subjects = model.subjects.map { quizUserSubjectEntityDomainMapper(it) }
        )
    }
}
