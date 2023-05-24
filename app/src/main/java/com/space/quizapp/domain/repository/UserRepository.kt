package com.space.quizapp.domain.repository

import com.space.quizapp.domain.model.UserDomainModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun saveUserInfo(userDomainModel: UserDomainModel)
    suspend fun retrieveUserInfo(username: String): Flow<UserDomainModel>
}
