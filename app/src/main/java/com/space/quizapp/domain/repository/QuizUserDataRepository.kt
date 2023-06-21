package com.space.quizapp.domain.repository

import com.space.quizapp.domain.model.QuizUserDomainModel
import kotlinx.coroutines.flow.Flow

interface QuizUserDataRepository {
    suspend fun saveUserInfo(userDomainModel: QuizUserDomainModel)
    suspend fun retrieveUserInfo(token: String): Flow<QuizUserDomainModel>
}
