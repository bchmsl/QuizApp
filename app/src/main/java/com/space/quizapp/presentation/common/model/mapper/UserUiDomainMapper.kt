package com.space.quizapp.presentation.common.model.mapper

import com.space.quizapp.common.mapper.ModelMapper
import com.space.quizapp.domain.model.UserDomainModel
import com.space.quizapp.presentation.common.model.UserUiModel

class UserUiDomainMapper : ModelMapper<UserUiModel, UserDomainModel> {
    override fun invoke(model: UserUiModel): UserDomainModel =
        UserDomainModel(
            username = model.userName,
            gpa = model.gpa
        )
}
