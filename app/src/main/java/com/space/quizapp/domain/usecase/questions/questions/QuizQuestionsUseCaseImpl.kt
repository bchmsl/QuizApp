package com.space.quizapp.domain.usecase.questions.questions

import com.space.quizapp.common.resource.Resource
import com.space.quizapp.domain.model.QuizQuestionsDomainModel
import com.space.quizapp.domain.usecase.questions.base.QuizBaseQuestionsUseCase
import kotlinx.coroutines.flow.Flow

class QuizQuestionsUseCaseImpl : QuizBaseQuestionsUseCase(), QuizQuestionsUseCase {
    override suspend fun invoke(): Flow<Resource<List<QuizQuestionsDomainModel>>> =
        repository.retrieveQuestions()
}
