package com.space.quizapp.domain.usecase.questions.get_questions

import com.space.quizapp.common.resource.QuizResource
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel
import kotlinx.coroutines.flow.Flow

interface QuizQuestionsUseCase {
    suspend operator fun invoke(): Flow<QuizResource<List<QuizSubjectDomainModel>>>
}
