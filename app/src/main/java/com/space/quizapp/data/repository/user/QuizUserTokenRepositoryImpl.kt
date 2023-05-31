package com.space.quizapp.data.repository.user

import com.space.quizapp.data.local.datastore.QuizUserDataStoreManager
import com.space.quizapp.domain.repository.user.QuizUserTokenRepository
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
