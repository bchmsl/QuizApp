package com.space.main.data.local.database.model.user.mapper

import com.space.main.data.local.database.model.user.UserEntity
import com.space.main.domain.model.user.UserDomainModel

class UserEntityMapper :
    com.space.common.mapper.EntityMapper<UserEntity, UserDomainModel> {
    override fun toDomain(model: UserEntity): UserDomainModel {
        return with(model) {
            UserDomainModel(
                username = username,
                token = token,
                gpa = gpa,
            )
        }
    }

    override fun toEntity(model: UserDomainModel): UserEntity {
        return with(model) {
            UserEntity(
                username = username,
                token = token,
                gpa = gpa,
            )
        }
    }
}
