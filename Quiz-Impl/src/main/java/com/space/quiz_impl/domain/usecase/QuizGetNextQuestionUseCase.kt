package com.space.quiz_impl.domain.usecase

import com.space.common.base.usecase.QuizBaseUseCase
import com.space.common.model.question.domain.QuizQuestionDomainModel
import com.space.quiz_api.GetNextQuestion

class QuizGetNextQuestionUseCase(
    private val quizRepository: GetNextQuestion,
) : QuizBaseUseCase<String, QuizQuestionDomainModel?>() {
    override suspend fun invoke(params: String?): QuizQuestionDomainModel? {
        return quizRepository(params!!)
    }
}
