package com.space.main.domain.usecase.user

import com.space.common.base.usecase.BaseUseCase
import com.space.common.util.Constants.EMPTY_STRING
import com.space.main.domain.model.user.UserDomainModel
import com.space.main.domain.repository.user.UserDataRepository
import java.util.UUID

class SaveUserDataUseCase(
    private val saveUserTokenUC: SaveUserTokenUseCase,
    private val userDataRepository: UserDataRepository,
) : BaseUseCase<UserDomainModel, Unit>() {

    override suspend fun invoke(params: UserDomainModel?) {
        val username = params!!.username
        var userToken: String? = null
        if (params.token == EMPTY_STRING) {
            userToken = userDataRepository.getUserTokenOrNull(username)
        }
        if (userToken == null) {
            userToken = UUID.randomUUID().toString()
            params.token = userToken
            userDataRepository.saveUserInfo(params)
        }
        saveUserTokenUC(userToken)
    }
}
