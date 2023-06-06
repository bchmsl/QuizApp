package com.space.quizapp.data.local.database.model.user.mapper.user

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.data.local.database.model.user.mapper.subject.QuizUserSubjectDomainEntityMapper
import com.space.quizapp.domain.model.user.QuizUserDomainModel

class QuizUserDomainEntityMapper(
    private val quizUserSubjectDomainEntityMapper: QuizUserSubjectDomainEntityMapper
) : QuizModelMapper<QuizUserDomainModel, QuizUserWithSubjectsEntity> {
    override fun invoke(model: QuizUserDomainModel): QuizUserWithSubjectsEntity {
        return QuizUserWithSubjectsEntity(
            username = model.username,
            token = model.token,
            gpa = model.gpa,
            subjects = model.subjects.map { quizUserSubjectDomainEntityMapper(it) }
        )
    }
}
