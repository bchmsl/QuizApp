package com.space.main_impl.domain.usecase.questions

import com.space.common.base.usecase.QuizBaseUseCase
import com.space.main_impl.domain.repository.quiz.QuizQuestionsRepository

class ResetQuestionAnsweredStatesUseCase(
    private val questionsRepository: QuizQuestionsRepository
) : QuizBaseUseCase<String, Unit>() {

    override suspend fun invoke(params: String?) {
        questionsRepository.resetAnsweredStates(params!!)
    }
}
