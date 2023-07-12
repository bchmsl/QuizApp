package com.space.main.presentation.model.user.mapper.user

import com.space.common.mapper.UiMapper
import com.space.main.domain.model.user.UserDomainModel
import com.space.main.presentation.model.user.UserUiModel

class UserUiMapper :
    UiMapper<UserUiModel, UserDomainModel> {
    override fun toDomain(model: UserUiModel): UserDomainModel {
        return with(model) {
            UserDomainModel(
                username = userName,
                gpa = gpa
            )
        }
    }

    override fun toUi(model: UserDomainModel): UserUiModel {
        return with(model) {
            UserUiModel(
                userName = username,
                gpa = gpa
            )
        }
    }
}
