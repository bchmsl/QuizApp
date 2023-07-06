package com.space.main_impl.data.local.database.model.user.mapper

import com.space.main_impl.data.local.database.model.user.QuizUserEntity
import com.space.main_impl.domain.model.user.QuizUserDomainModel

class QuizUserEntityMapper :
    com.space.common.mapper.QuizEntityMapper<QuizUserEntity, QuizUserDomainModel> {
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
