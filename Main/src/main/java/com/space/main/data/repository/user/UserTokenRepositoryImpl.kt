package com.space.main.data.repository.user

import com.space.main.data.local.datastore.UserDataStoreManager
import com.space.main.domain.repository.user.UserTokenRepository
import kotlinx.coroutines.flow.first

class UserTokenRepositoryImpl(
    private val userDataStoreManager: UserDataStoreManager
) : UserTokenRepository() {

    override suspend fun saveUserToken(token: String) {
        userDataStoreManager.saveValue(token)
    }

    override suspend fun retrieveUserToken(): String {
        return userDataStoreManager.readValue().first()
    }
}
