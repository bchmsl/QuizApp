package com.space.main.data.local.database.model.user.mapper

import com.space.main.data.local.database.model.user.QuizUserSubjectEntity
import com.space.main.domain.model.user.QuizUserSubjectDomainModel

class QuizUserSubjectEntityMapper :
    com.space.common.mapper.QuizEntityMapper<QuizUserSubjectEntity, QuizUserSubjectDomainModel> {
    override fun toDomain(model: QuizUserSubjectEntity): QuizUserSubjectDomainModel {
        return with(model) {
            QuizUserSubjectDomainModel(
                username = username,
                quizTitle = quizTitle,
                score = score,
                questionsCount = questionsCount,
                maxScore = maxScore
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
                questionsCount = questionsCount,
                maxScore = maxScore
            )
        }
    }
}
