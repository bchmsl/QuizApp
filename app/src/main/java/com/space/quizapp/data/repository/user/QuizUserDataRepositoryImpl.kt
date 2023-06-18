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

    override suspend fun retrieveUserInfo(token: String): QuizUserDomainModel {
        return userMapper.toDomain(userDao.retrieveUserInfo(token))
    }

    override suspend fun getUserTokenIfExists(username: String): String? {
        return userDao.checkUser(username)
    }

}
