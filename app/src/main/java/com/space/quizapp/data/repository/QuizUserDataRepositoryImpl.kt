package com.space.quizapp.data.repository

import android.util.Log
import com.space.quizapp.data.local.database.dao.QuizUserDao
import com.space.quizapp.data.local.database.model.mapper.QuizUserDomainEntityMapper
import com.space.quizapp.data.local.database.model.mapper.QuizUserEntityDomainMapper
import com.space.quizapp.domain.model.QuizUserDomainModel
import com.space.quizapp.domain.repository.QuizUserDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuizUserDataRepositoryImpl(
    private val dao: QuizUserDao,
    private val userDomainEntityMapper: QuizUserDomainEntityMapper,
    private val userEntityDomainMapper: QuizUserEntityDomainMapper
) : QuizUserDataRepository {

    override suspend fun saveUserInfo(userDomainModel: QuizUserDomainModel) {
        Log.d("TAG_REPO", userDomainModel.toString())
        dao.saveUser(userDomainEntityMapper(userDomainModel))
    }

    override suspend fun retrieveUserInfo(token: String): Flow<QuizUserDomainModel> {
        Log.d("TAG_REPO", token)
        return dao.retrieveUserInfo(token).map {
            userEntityDomainMapper(it[0])
        }
    }

    override suspend fun getUserTokenIfExists(username: String): String? {
        return dao.checkUser(username)
    }
}
