package com.space.quizapp.presentation.common.model.mapper

import com.space.quizapp.common.mapper.ModelMapper
import com.space.quizapp.domain.model.UserDomainModel
import com.space.quizapp.presentation.common.model.UserUiModel

class UserDomainUiMapper : ModelMapper<UserDomainModel, UserUiModel> {
    override fun invoke(model: UserDomainModel): UserUiModel =
        UserUiModel(
            userName = model.username,
            gpa = model.gpa,
        )

}
