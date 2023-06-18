package com.space.quizapp.data.local.database.model.user.mapper

import com.space.quizapp.common.mapper.QuizEntityMapper
import com.space.quizapp.data.local.database.model.user.QuizUserEntity
import com.space.quizapp.domain.model.user.QuizUserDomainModel

class QuizUserEntityMapper : QuizEntityMapper<QuizUserEntity, QuizUserDomainModel> {
    override fun toDomain(model: QuizUserEntity): QuizUserDomainModel {
        return with(model) {
            QuizUserDomainModel(
                username = username,
                token = token,
                gpa = gpa,
            )
        }
    }

    override fun toEntity(model: QuizUserDomainModel): QuizUserEntity {
        return with(model) {
            QuizUserEntity(
                username = username,
                token = token,
                gpa = gpa,
            )
        }
    }
}
