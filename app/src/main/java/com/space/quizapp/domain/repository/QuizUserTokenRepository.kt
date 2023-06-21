package com.space.quizapp.domain.repository

import kotlinx.coroutines.flow.Flow


interface QuizUserTokenRepository {
    suspend fun saveUserToken(token: String)
    suspend fun getUserToken(): Flow<String>
}
