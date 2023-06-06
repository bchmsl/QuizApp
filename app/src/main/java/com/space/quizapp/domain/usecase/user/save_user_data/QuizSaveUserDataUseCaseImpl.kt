package com.space.quizapp.domain.usecase.user.save_user_data

import com.space.quizapp.common.util.QuizValidateUser
import com.space.quizapp.data.local.datastore.QuizUserDataStoreManager.Companion.EMPTY_STRING
import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.domain.usecase.user.save_user_token.QuizSaveUserTokenUseCase
import java.util.*

class QuizSaveUserDataUseCaseImpl(
    private val saveUserTokenUseCase: QuizSaveUserTokenUseCase,
) : QuizSaveUserDataUseCase() {
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
