package com.space.quizapp.domain.usecase.saveuser

import com.space.quizapp.common.util.ValidateUser
import com.space.quizapp.domain.model.UserDomainModel
import com.space.quizapp.domain.usecase.user.base.BaseUserUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SaveUserUseCaseImpl : BaseUserUseCase(), SaveUserUseCase {
    override suspend fun invoke(userDomainModel: UserDomainModel): Flow<ValidateUser> = flow {
        val userName = userDomainModel.username
        val userNameValid: ValidateUser = ValidateUser.validate(userName)
        if (userNameValid == ValidateUser.VALID) {
            repository.saveUserInfo(userDomainModel)
        }
        emit(userNameValid)
    }
}
