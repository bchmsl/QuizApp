package com.space.quizapp.data.repository

import com.space.quizapp.data.local.database.dao.UserDao
import com.space.quizapp.data.local.database.model.mapper.UserDomainEntityMapper
import com.space.quizapp.data.local.database.model.mapper.UserEntityDomainMapper
import com.space.quizapp.domain.model.UserDomainModel
import com.space.quizapp.domain.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataRepositoryImpl(
    private val dao: UserDao,
    private val userDomainEntityMapper: UserDomainEntityMapper,
    private val userEntityDomainMapper: UserEntityDomainMapper
) : UserDataRepository {

    override suspend fun saveUserInfo(userDomainModel: UserDomainModel) {
        dao.saveUser(userDomainEntityMapper(userDomainModel))
    }

    override suspend fun retrieveUserInfo(token: String): Flow<UserDomainModel> {
        return dao.retrieveUserInfo(token).map {
            userEntityDomainMapper(it[0])
        }
    }
}
