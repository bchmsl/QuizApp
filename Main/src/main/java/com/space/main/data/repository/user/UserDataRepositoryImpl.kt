package com.space.main.data.repository.user

import com.space.main.data.local.database.dao.UserDao
import com.space.main.data.local.database.model.user.mapper.UserEntityMapper
import com.space.main.domain.model.user.UserDomainModel
import com.space.main.domain.repository.user.UserDataRepository

class UserDataRepositoryImpl(
    private val userDao: UserDao,
    private val userMapper: UserEntityMapper
) : UserDataRepository() {

    override suspend fun saveUserInfo(userDomainModel: UserDomainModel) {
        userDao.saveUser(userMapper.toEntity(userDomainModel))
    }

    override suspend fun retrieveUser(username: String): UserDomainModel {
        return userMapper.toDomain(userDao.getUser(username))
    }

    override suspend fun retrieveUserByToken(userToken: String): UserDomainModel {
        return userMapper.toDomain(userDao.getUserByToken(userToken))
    }

    override suspend fun getUserTokenOrNull(username: String): String? {
        return userDao.getUserTokenOrNull(username)
    }

    override suspend fun updateGPA(username: String, gpa: Double) {
        userDao.saveUser(userDao.getUser(username).copy(gpa = gpa.toFloat()))
    }
}
