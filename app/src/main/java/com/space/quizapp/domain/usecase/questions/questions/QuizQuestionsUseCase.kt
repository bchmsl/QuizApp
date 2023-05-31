package com.space.quizapp.domain.usecase.questions.questions

import com.space.quizapp.common.resource.QuizResource
import com.space.quizapp.domain.model.quiz.QuizQuestionsDomainModel
import kotlinx.coroutines.flow.Flow

interface QuizQuestionsUseCase {
    suspend operator fun invoke(): Flow<QuizResource<List<QuizQuestionsDomainModel>>>
}
