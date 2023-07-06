package com.space.main_impl.domain.repository.user

import com.space.main_impl.domain.model.user.QuizUserDomainModel

abstract class QuizUserDataRepository {
    abstract suspend fun saveUserInfo(userDomainModel: QuizUserDomainModel)
    abstract suspend fun retrieveUser(username: String): QuizUserDomainModel
    abstract suspend fun retrieveUserByToken(userToken: String): QuizUserDomainModel
    abstract suspend fun getUserTokenOrNull(username: String): String?
    abstract suspend fun updateGPA(username: String, gpa: Double)
}