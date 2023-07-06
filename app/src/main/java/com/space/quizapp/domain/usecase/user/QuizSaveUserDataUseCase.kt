package com.space.quizapp.domain.usecase.user

import com.space.common.util.QuizConstants.EMPTY_STRING
import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.domain.repository.user.QuizUserDataRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
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
