package com.space.main_impl.data.repository.user

import com.space.main_impl.data.local.database.dao.QuizUserDao
import com.space.main_impl.data.local.database.model.user.mapper.QuizUserEntityMapper
import com.space.main_impl.domain.model.user.QuizUserDomainModel
import com.space.main_impl.domain.repository.user.QuizUserDataRepository

class QuizUserDataRepositoryImpl(
    private val userDao: QuizUserDao,
    private val userMapper: QuizUserEntityMapper
) : QuizUserDataRepository() {

    override suspend fun saveUserInfo(userDomainModel: QuizUserDomainModel) {
        userDao.saveUser(userMapper.toEntity(userDomainModel))
    }

    override suspend fun retrieveUser(username: String): QuizUserDomainModel {
        return userMapper.toDomain(userDao.getUser(username))
    }

    override suspend fun retrieveUserByToken(userToken: String): QuizUserDomainModel {
        return userMapper.toDomain(userDao.getUserByToken(userToken))
    }

    override suspend fun getUserTokenOrNull(username: String): String? {
        return userDao.getUserTokenOrNull(username)
    }

    override suspend fun updateGPA(username: String, gpa: Double) {
        userDao.saveUser(userDao.getUser(username).copy(gpa = gpa.toFloat()))
    }
}
