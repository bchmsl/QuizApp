package com.space.quizapp.domain.repository.user

import com.space.quizapp.data.repository.BaseRepository
import com.space.quizapp.domain.model.user.QuizUserDomainModel

abstract class QuizUserDataRepository : BaseRepository() {
    abstract suspend fun saveUserInfo(userDomainModel: QuizUserDomainModel)
    abstract suspend fun retrieveUser(token: String): QuizUserDomainModel
    abstract suspend fun getUserTokenOrNull(username: String): String?
}
