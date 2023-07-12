package com.space.main.domain.repository.user

import com.space.main.domain.model.user.UserDomainModel

abstract class UserDataRepository {
    abstract suspend fun saveUserInfo(userDomainModel: UserDomainModel)
    abstract suspend fun retrieveUser(username: String): UserDomainModel
    abstract suspend fun retrieveUserByToken(userToken: String): UserDomainModel
    abstract suspend fun getUserTokenOrNull(username: String): String?
    abstract suspend fun updateGPA(username: String, gpa: Double)
}
