package com.space.quizapp.data.local.database.model.mapper

import com.space.quizapp.common.mapper.ModelMapper
import com.space.quizapp.data.local.database.model.UserEntity
import com.space.quizapp.domain.model.UserDomainModel

class UserDomainEntityMapper : ModelMapper<UserDomainModel, UserEntity> {
    override fun invoke(model: UserDomainModel): UserEntity =
        UserEntity(
            username = model.username,
            gpa = model.gpa
        )
}
