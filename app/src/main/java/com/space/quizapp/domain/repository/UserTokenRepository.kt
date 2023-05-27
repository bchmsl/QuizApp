package com.space.quizapp.domain.repository

import kotlinx.coroutines.flow.Flow


interface UserTokenRepository {
    suspend fun saveUserToken(token: String)
    suspend fun getUserToken(): Flow<String>
}
