package com.space.quizapp.domain.usecase.user.read_user_data

import com.space.quizapp.data.local.datastore.UserDataStoreManager.Companion.EMPTY_STRING
import com.space.quizapp.domain.model.UserDomainModel
import com.space.quizapp.domain.usecase.user.base.BaseUserDataUseCase
import com.space.quizapp.domain.usecase.user.read_user_token.ReadUserTokenUseCase
import kotlinx.coroutines.flow.Flow

class RetrieveUserDataDataUseCaseImpl(
    private val readUserTokenUC: ReadUserTokenUseCase
) : BaseUserDataUseCase(), RetrieveUserDataUseCase {
    override suspend fun invoke(): Flow<UserDomainModel> {
        var userToken = EMPTY_STRING
        readUserTokenUC().collect { token ->
            if (token != EMPTY_STRING) {
                userToken = token
            } else {
                throw RuntimeException("User token not found!")
            }
        }
        return repository.retrieveUserInfo(userToken)
    }
}
