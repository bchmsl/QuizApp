package com.space.main_impl.domain.usecase.user

import com.space.common.base.usecase.QuizBaseUseCase
import com.space.main_impl.domain.repository.user.QuizUserTokenRepository

class QuizSaveUserTokenUseCase(
    private val userTokenRepository: QuizUserTokenRepository
) : QuizBaseUseCase<String, Unit>() {

    override suspend fun invoke(params: String?) {
        userTokenRepository.saveUserToken(params!!)
    }
}
