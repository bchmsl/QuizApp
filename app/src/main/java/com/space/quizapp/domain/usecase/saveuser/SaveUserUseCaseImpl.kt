package com.space.quizapp.domain.usecase.saveuser

import com.space.quizapp.common.util.ValidateUser
import com.space.quizapp.domain.model.UserDomainModel
import com.space.quizapp.domain.usecase.user.base.BaseUserUseCase

class SaveUserUseCaseImpl : BaseUserUseCase(), SaveUserUseCase {
    override suspend fun invoke(userDomainModel: UserDomainModel): ValidateUser? {
        val userName = userDomainModel.username
        var userNameValid: ValidateUser? = ValidateUser.validate(userName)
        if (userNameValid == ValidateUser.VALID) {
            repository.saveUserInfo(userDomainModel)
            userNameValid = null
        }
        return userNameValid
    }
}
