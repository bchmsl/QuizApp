package com.space.quiz_impl.domain.usecase

import com.space.common.base.usecase.QuizBaseUseCase
import com.space.quiz_impl.domain.model.QuizQuestionDomainModel
import com.space.quiz_impl.domain.repository.QuizRepository

class QuizGetNextQuestionUseCase(
    private val quizRepository: QuizRepository,
) : QuizBaseUseCase<String, QuizQuestionDomainModel?>() {
    override suspend fun invoke(params: String?): QuizQuestionDomainModel? {
        return quizRepository.getNextQuestion(params!!)
    }
}
