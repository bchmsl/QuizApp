package com.space.quizapp.domain.usecase.questions.questions

import com.space.quizapp.common.resource.Resource
import com.space.quizapp.domain.model.QuizQuestionsDomainModel
import kotlinx.coroutines.flow.Flow

interface QuizQuestionsUseCase {
    suspend operator fun invoke(): Flow<Resource<List<QuizQuestionsDomainModel>>>
}
