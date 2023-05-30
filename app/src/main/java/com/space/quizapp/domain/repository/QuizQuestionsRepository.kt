package com.space.quizapp.domain.repository

import com.space.quizapp.common.resource.Resource
import com.space.quizapp.domain.model.QuizQuestionsDomainModel
import kotlinx.coroutines.flow.Flow

interface QuizQuestionsRepository {
    suspend fun retrieveQuestions(): Flow<Resource<List<QuizQuestionsDomainModel>>>
}
