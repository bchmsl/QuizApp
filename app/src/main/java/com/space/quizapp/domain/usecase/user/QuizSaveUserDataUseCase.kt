package com.space.quizapp.domain.usecase.user

import com.space.quizapp.common.util.QuizValidateUser
import com.space.quizapp.data.local.datastore.QuizUserDataStoreManager.Companion.EMPTY_STRING
import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.domain.repository.user.QuizUserDataRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import java.util.*

class QuizSaveUserDataUseCase(
    private val saveUserTokenUC: QuizBaseUseCase<String, Unit>,
    private val repository: QuizUserDataRepository
) : QuizBaseUseCase<QuizUserDomainModel, QuizValidateUser>() {

    override suspend fun invoke(userDomainModel: QuizUserDomainModel?): QuizValidateUser {
        val username = userDomainModel!!.username
        val userNameValid: QuizValidateUser = QuizValidateUser.validate(username)
        if (userNameValid != QuizValidateUser.VALID) return userNameValid
        var userToken: String? = null
        if (userDomainModel.token != EMPTY_STRING) {
            userToken = repository.getUserTokenOrNull(username)
        }
        if (userToken == null) {
            userToken = UUID.randomUUID().toString()
            userDomainModel.token = userToken
            repository.saveUserInfo(userDomainModel)
        }
        saveUserTokenUC(userToken)
        return userNameValid
    }
}
