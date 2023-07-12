package com.space.main.domain.usecase.user

import com.space.common.base.usecase.BaseUseCase
import com.space.main.domain.repository.user.UserTokenRepository

class SaveUserTokenUseCase(
    private val userTokenRepository: UserTokenRepository
) : BaseUseCase<String, Unit>() {

    override suspend fun invoke(params: String?) {
        userTokenRepository.saveUserToken(params!!)
    }
}
