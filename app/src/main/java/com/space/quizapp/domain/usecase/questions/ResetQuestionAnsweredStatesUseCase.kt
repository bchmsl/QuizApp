package com.space.quizapp.domain.usecase.questions

import com.space.quizapp.data.repository.quiz.QuizQuestionsRepositoryImpl
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase

class ResetQuestionAnsweredStatesUseCase(
    private val questionsRepository: QuizQuestionsRepositoryImpl
) : QuizBaseUseCase<String, Unit>() {

    override suspend fun invoke(params: String?) {
        questionsRepository.resetAnsweredStates(params!!)
    }
}
