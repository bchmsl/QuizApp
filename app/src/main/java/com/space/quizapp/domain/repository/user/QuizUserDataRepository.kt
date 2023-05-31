package com.space.quizapp.domain.repository.user

import com.space.quizapp.domain.model.user.QuizUserDomainModel
import kotlinx.coroutines.flow.Flow

interface QuizUserDataRepository {
    suspend fun saveUserInfo(userDomainModel: QuizUserDomainModel)
    suspend fun retrieveUserInfo(token: String): Flow<QuizUserDomainModel>
    suspend fun getUserTokenIfExists(username: String): String?
}
