package com.space.quizapp.data.local.database.model.user.mapper

import com.space.quizapp.common.mapper.QuizEntityMapper
import com.space.quizapp.data.local.database.model.user.QuizUserSubjectEntity
import com.space.quizapp.domain.model.user.QuizUserSubjectDomainModel

class QuizUserSubjectEntityMapper :
    QuizEntityMapper<QuizUserSubjectEntity, QuizUserSubjectDomainModel> {
    override fun toDomain(model: QuizUserSubjectEntity): QuizUserSubjectDomainModel {
        return with(model) {
            QuizUserSubjectDomainModel(
                username = username,
                quizTitle = quizTitle,
                score = score,
                questionsCount = questionsCount
            )
        }
    }

    override fun toEntity(model: QuizUserSubjectDomainModel): QuizUserSubjectEntity {
        return with(model) {
            QuizUserSubjectEntity(
                id = 0,
                username = username,
                quizTitle = quizTitle,
                score = score,
                questionsCount = questionsCount
            )
        }
    }
}
