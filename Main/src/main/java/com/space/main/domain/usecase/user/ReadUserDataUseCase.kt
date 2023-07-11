package com.space.main.domain.usecase.user

import com.space.common.base.usecase.BaseUseCase
import com.space.common.util.Constants.EMPTY_STRING
import com.space.main.domain.model.user.UserDomainModel
import com.space.main.domain.repository.user.UserDataRepository

class ReadUserDataUseCase(
    private val readUserTokenUC: ReadUserTokenUseCase,
    private val userDataRepository: UserDataRepository
) : BaseUseCase<Unit, UserDomainModel>() {

    override suspend fun invoke(params: Unit?): UserDomainModel {
        val userToken = readUserTokenUC()
        return when {
            userToken != EMPTY_STRING -> userDataRepository.retrieveUserByToken(userToken)
            else -> throw RuntimeException("User Token Not Found")
        }
    }
}
