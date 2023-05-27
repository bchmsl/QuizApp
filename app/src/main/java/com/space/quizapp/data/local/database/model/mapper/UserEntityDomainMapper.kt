package com.space.quizapp.data.local.database.model.mapper

import com.space.quizapp.common.mapper.ModelMapper
import com.space.quizapp.data.local.database.model.UserEntity
import com.space.quizapp.domain.model.UserDomainModel

class UserEntityDomainMapper : ModelMapper<UserEntity, UserDomainModel> {
    override fun invoke(model: UserEntity): UserDomainModel =
        UserDomainModel(
            username = model.username,
            token = model.token,
            gpa = model.gpa,
        )
}
