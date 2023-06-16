package com.space.quizapp.domain.usecase.user

import com.space.quizapp.common.util.QuizValidateUser
import com.space.quizapp.data.local.datastore.QuizUserDataStoreManager.Companion.EMPTY_STRING
import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.domain.repository.user.QuizUserDataRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import java.util.*

class QuizSaveUserDataUseCase(
    private val saveUserTokenUseCase: QuizBaseUseCase<String, Unit>,
    private val repository: QuizUserDataRepository
) : QuizBaseUseCase<QuizUserDomainModel, QuizValidateUser>() {

    override suspend fun invoke(userDomainModel: QuizUserDomainModel?): QuizValidateUser {
        val username = userDomainModel!!.username
        val userNameValid: QuizValidateUser = QuizValidateUser.validate(username)
        if (userNameValid == QuizValidateUser.VALID) {
            if (userDomainModel.token == EMPTY_STRING) {
                var userToken = repository.getUserTokenIfExists(username)
                if (userToken == null) {
                    userToken = UUID.randomUUID().toString()
                    userDomainModel.token = userToken
                    repository.saveUserInfo(userDomainModel)
                }
                saveUserTokenUseCase(userToken)
            }
        }
        return userNameValid
    }
}
