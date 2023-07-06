package com.space.main_impl.domain.repository.user

abstract class QuizUserTokenRepository {
    abstract suspend fun saveUserToken(token: String)
    abstract suspend fun retrieveUserToken(): String
}
