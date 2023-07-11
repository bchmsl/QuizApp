package com.space.main.data.local.database.model.user.mapper

import com.space.main.data.local.database.model.user.UserSubjectEntity
import com.space.main.domain.model.user.UserSubjectDomainModel

class UserSubjectEntityMapper :
    com.space.common.mapper.EntityMapper<UserSubjectEntity, UserSubjectDomainModel> {
    override fun toDomain(model: UserSubjectEntity): UserSubjectDomainModel {
        return with(model) {
            UserSubjectDomainModel(
                username = username,
                quizTitle = quizTitle,
                score = score,
                questionsCount = questionsCount,
                maxScore = maxScore
            )
        }
    }

    override fun toEntity(model: UserSubjectDomainModel): UserSubjectEntity {
        return with(model) {
            UserSubjectEntity(
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
