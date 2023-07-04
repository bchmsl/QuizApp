package com.space.quizapp.domain.usecase.user

import com.space.quizapp.common.util.QuizUserValidation
import com.space.quizapp.data.local.datastore.QuizUserDataStoreManager.Companion.EMPTY_STRING
import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.domain.repository.user.QuizUserDataRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import java.util.*

class QuizSaveUserDataUseCase(
    private val saveUserTokenUC: QuizSaveUserTokenUseCase,
    private val userDataRepository: QuizUserDataRepository
) : QuizBaseUseCase<QuizUserDomainModel, QuizUserValidation>() {

    override suspend fun invoke(params: QuizUserDomainModel?): QuizUserValidation {
        val username = params!!.username
        val userNameValid: QuizUserValidation = QuizUserValidation.validateUser(username)
        if (userNameValid != QuizUserValidation.VALID) return userNameValid
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
        return userNameValid
    }
}
