package com.space.quiz_impl.domain.usecase

import com.space.common.base.usecase.QuizBaseUseCase
import com.space.quiz_impl.domain.repository.QuizRepository

class GetQuestionsCountUseCase(
    private val quizRepository: QuizRepository
) : QuizBaseUseCase<String, Int>() {
    override suspend fun invoke(params: String?): Int {
        return quizRepository.getQuestionsCount(params!!)
    }
}