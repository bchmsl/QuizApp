package com.space.quizapp.data.repository.user

import com.space.quizapp.data.local.database.dao.QuizUserDao
import com.space.quizapp.data.local.database.model.user.mapper.user.QuizUserDomainEntityMapper
import com.space.quizapp.data.local.database.model.user.mapper.user.QuizUserEntityDomainMapper
import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.domain.repository.user.QuizUserDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuizUserDataRepositoryImpl(
    private val dao: QuizUserDao,
    private val userDomainEntityMapper: QuizUserDomainEntityMapper,
    private val userEntityDomainMapper: QuizUserEntityDomainMapper
) : QuizUserDataRepository {

    override suspend fun saveUserInfo(userDomainModel: QuizUserDomainModel) {
        dao.saveUser(userDomainEntityMapper(userDomainModel))
    }

    override suspend fun retrieveUserInfo(token: String): Flow<QuizUserDomainModel> =
        dao.retrieveUserInfo(token).map {
            userEntityDomainMapper(it[0])

        }

    override suspend fun getUserTokenIfExists(username: String): String? = dao.checkUser(username)

}
