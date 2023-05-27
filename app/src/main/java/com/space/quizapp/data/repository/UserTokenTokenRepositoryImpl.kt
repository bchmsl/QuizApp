package com.space.quizapp.data.repository

import com.space.quizapp.data.local.datastore.UserDataStoreManager
import com.space.quizapp.domain.repository.UserTokenRepository
import kotlinx.coroutines.flow.Flow

class UserTokenTokenRepositoryImpl(
    private val userDataStoreManager: UserDataStoreManager
) : UserTokenRepository {

    override suspend fun saveUserToken(token: String) {
        userDataStoreManager.saveValue(token)
    }

    override suspend fun getUserToken(): Flow<String> =
        userDataStoreManager.readValue()
}
