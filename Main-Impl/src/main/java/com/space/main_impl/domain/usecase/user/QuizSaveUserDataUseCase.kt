package com.space.main_impl.domain.usecase.user

import com.space.common.base.usecase.QuizBaseUseCase
import com.space.common.util.QuizConstants.EMPTY_STRING
import com.space.main_impl.domain.model.user.QuizUserDomainModel
import com.space.main_impl.domain.repository.user.QuizUserDataRepository
import java.util.UUID

class QuizSaveUserDataUseCase(
    private val saveUserTokenUC: QuizSaveUserTokenUseCase,
    private val userDataRepository: QuizUserDataRepository,
) : QuizBaseUseCase<QuizUserDomainModel, Unit>() {

    override suspend fun invoke(params: QuizUserDomainModel?) {
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
