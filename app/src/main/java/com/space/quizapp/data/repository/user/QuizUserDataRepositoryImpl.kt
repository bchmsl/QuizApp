package com.space.quizapp.data.repository.user

import com.space.quizapp.data.local.database.dao.QuizUserDao
import com.space.quizapp.data.local.database.model.user.mapper.user.QuizUserDomainEntityMapper
import com.space.quizapp.data.local.database.model.user.mapper.user.QuizUserEntityDomainMapper
import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.domain.repository.user.QuizUserDataRepository

class QuizUserDataRepositoryImpl(
    private val dao: QuizUserDao,
    private val userDomainEntityMapper: QuizUserDomainEntityMapper,
    private val userEntityDomainMapper: QuizUserEntityDomainMapper
) : QuizUserDataRepository() {

    override suspend fun saveUserInfo(userDomainModel: QuizUserDomainModel) {
        dao.saveUser(userDomainEntityMapper(userDomainModel))
    }

    override suspend fun retrieveUserInfo(token: String): QuizUserDomainModel {
        return userEntityDomainMapper(dao.retrieveUserInfo(token))
    }

    override suspend fun getUserTokenIfExists(username: String): String? {
        return dao.checkUser(username)
    }

}
