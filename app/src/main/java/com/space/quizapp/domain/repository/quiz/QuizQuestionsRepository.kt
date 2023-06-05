package com.space.quizapp.domain.repository.quiz

import com.space.quizapp.common.resource.QuizResource
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel
import kotlinx.coroutines.flow.Flow

interface QuizQuestionsRepository {
    suspend fun retrieveQuestions(): Flow<QuizResource<List<QuizSubjectDomainModel>>>
}
