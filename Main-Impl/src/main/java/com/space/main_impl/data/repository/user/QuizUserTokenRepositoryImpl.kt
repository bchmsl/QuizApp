package com.space.main_impl.data.repository.user

import com.space.main_impl.data.local.datastore.QuizUserDataStoreManager
import com.space.main_impl.domain.repository.user.QuizUserTokenRepository
import kotlinx.coroutines.flow.first

class QuizUserTokenRepositoryImpl(
    private val userDataStoreManager: QuizUserDataStoreManager
) : QuizUserTokenRepository() {

    override suspend fun saveUserToken(token: String) {
        userDataStoreManager.saveValue(token)
    }

    override suspend fun retrieveUserToken(): String {
        return userDataStoreManager.readValue().first()
    }
}
