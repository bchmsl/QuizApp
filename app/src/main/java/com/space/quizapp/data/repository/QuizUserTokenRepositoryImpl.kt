package com.space.quizapp.data.repository

import com.space.quizapp.data.local.datastore.QuizUserDataStoreManager
import com.space.quizapp.domain.repository.QuizUserTokenRepository
import kotlinx.coroutines.flow.Flow

class QuizUserTokenRepositoryImpl(
    private val userDataStoreManager: QuizUserDataStoreManager
) : QuizUserTokenRepository {

    override suspend fun saveUserToken(token: String) {
        userDataStoreManager.saveValue(token)
    }

    override suspend fun getUserToken(): Flow<String> =
        userDataStoreManager.readValue()
}
