package com.space.quizapp.data.repository.user

import com.space.quizapp.data.local.database.dao.QuizUserDao
import com.space.quizapp.data.local.database.model.user.mapper.QuizUserEntityMapper
import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.domain.repository.user.QuizUserDataRepository

class QuizUserDataRepositoryImpl(
    private val userDao: QuizUserDao,
    private val userMapper: QuizUserEntityMapper
) : QuizUserDataRepository() {

    override suspend fun saveUserInfo(userDomainModel: QuizUserDomainModel) {
        userDao.saveUser(userMapper.toEntity(userDomainModel))
    }

    override suspend fun retrieveUser(token: String): QuizUserDomainModel {
        return userMapper.toDomain(userDao.getUser(token))
    }

    override suspend fun getUserTokenOrNull(username: String): String? {
        return userDao.getUserTokenOrNull(username)
    }

    override suspend fun updateGPA(token: String, gpa: Double) {
        userDao.saveUser(userDao.getUser(token).copy(gpa = gpa.toFloat()))
    }
}
