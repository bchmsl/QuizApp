package com.space.quiz_impl.domain.usecase

import com.space.common.base.usecase.BaseUseCase
import com.space.common.model.question.domain.QuestionDomainModel
import com.space.quiz_api.GetNextQuestion

class GetNextQuestionUseCase(
    private val getNextQuestion: GetNextQuestion,
) : BaseUseCase<String, QuestionDomainModel?>() {
    override suspend fun invoke(params: String?): QuestionDomainModel? {
        return getNextQuestion(params!!)
    }
}
