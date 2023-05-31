package com.space.quizapp.domain.usecase.questions.questions

import com.space.quizapp.common.resource.QuizResource
import com.space.quizapp.domain.model.quiz.QuizQuestionsDomainModel
import com.space.quizapp.domain.usecase.questions.base.QuizBaseQuestionsUseCase
import kotlinx.coroutines.flow.Flow

class QuizQuestionsUseCaseImpl : QuizBaseQuestionsUseCase(), QuizQuestionsUseCase {
    override suspend fun invoke(): Flow<QuizResource<List<QuizQuestionsDomainModel>>> =
        repository.retrieveQuestions()
}
