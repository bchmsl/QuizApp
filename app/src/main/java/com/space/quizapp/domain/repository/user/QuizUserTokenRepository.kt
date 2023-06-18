package com.space.quizapp.domain.repository.user

import com.space.quizapp.data.repository.BaseRepository

abstract class QuizUserTokenRepository : BaseRepository() {
    abstract suspend fun saveUserToken(token: String)
    abstract suspend fun retrieveUserToken(): String
}
