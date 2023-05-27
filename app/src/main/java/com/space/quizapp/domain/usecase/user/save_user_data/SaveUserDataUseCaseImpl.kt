package com.space.quizapp.domain.usecase.user.save_user_data

import com.space.quizapp.common.util.ValidateUser
import com.space.quizapp.data.local.datastore.UserDataStoreManager.Companion.EMPTY_STRING
import com.space.quizapp.domain.model.UserDomainModel
import com.space.quizapp.domain.usecase.user.base.BaseUserDataUseCase
import com.space.quizapp.domain.usecase.user.save_user_token.SaveUserTokenUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class SaveUserDataUseCaseImpl(
    private val saveUserTokenUseCase: SaveUserTokenUseCase
) : BaseUserDataUseCase(), SaveUserDataUseCase {
    override suspend fun invoke(userDomainModel: UserDomainModel): Flow<ValidateUser> = flow {
        val userName = userDomainModel.username
        val userNameValid: ValidateUser = ValidateUser.validate(userName)
        if (userNameValid == ValidateUser.VALID) {
            if (userDomainModel.token == EMPTY_STRING) {
                val userToken = UUID.randomUUID().toString()
                userDomainModel.token = userToken
                saveUserTokenUseCase(userToken)
            }
            repository.saveUserInfo(userDomainModel)
        }
        emit(userNameValid)
    }
}
