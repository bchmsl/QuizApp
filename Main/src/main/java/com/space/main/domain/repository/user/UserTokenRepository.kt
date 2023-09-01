package com.space.main.domain.repository.user

abstract class UserTokenRepository {
    abstract suspend fun saveUserToken(token: String)
    abstract suspend fun retrieveUserToken(): String
}
