package com.space.quizapp.domain.usecase.questions

import com.space.common.base.usecase.QuizBaseUseCase
import com.space.quizapp.data.repository.quiz.QuizQuestionsRepositoryImpl

class ResetQuestionAnsweredStatesUseCase(
    private val questionsRepository: QuizQuestionsRepositoryImpl
) : com.space.common.base.usecase.QuizBaseUseCase<String, Unit>() {

    override suspend fun invoke(params: String?) {
        questionsRepository.resetAnsweredStates(params!!)
    }
}
