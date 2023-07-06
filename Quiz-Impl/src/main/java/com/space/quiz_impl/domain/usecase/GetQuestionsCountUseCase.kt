package com.space.quiz_impl.domain.usecase

import com.space.common.base.usecase.QuizBaseUseCase
import com.space.quiz_api.GetQuestionsCount

class GetQuestionsCountUseCase(
    private val getQuestionsCount: GetQuestionsCount
) : QuizBaseUseCase<String, Int>() {
    override suspend fun invoke(params: String?): Int {
        return getQuestionsCount(params!!)
    }
}