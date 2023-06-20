package com.space.quizapp.domain.repository.user

import com.space.quizapp.data.repository.BaseRepository
import com.space.quizapp.domain.model.user.QuizUserDomainModel

abstract class QuizUserDataRepository : BaseRepository() {
    abstract suspend fun saveUserInfo(userDomainModel: QuizUserDomainModel)
    abstract suspend fun retrieveUser(username: String): QuizUserDomainModel
    abstract suspend fun retrieveUserByToken(userToken: String): QuizUserDomainModel
    abstract suspend fun getUserTokenOrNull(username: String): String?
    abstract suspend fun updateGPA(username: String, gpa: Double)
}
