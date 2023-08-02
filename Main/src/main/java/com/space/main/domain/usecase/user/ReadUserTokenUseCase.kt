package com.space.main.domain.usecase.user

import com.space.common.base.usecase.BaseUseCase
import com.space.main.domain.repository.user.UserTokenRepository

class ReadUserTokenUseCase(
    private val userTokenRepository: UserTokenRepository
) : BaseUseCase<Unit, String>() {
    override suspend fun invoke(params: Unit?): String {
        return userTokenRepository.retrieveUserToken()
    }
}
