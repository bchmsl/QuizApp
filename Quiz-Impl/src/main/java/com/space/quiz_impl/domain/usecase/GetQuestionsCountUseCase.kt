package com.space.quiz_impl.domain.usecase

import com.space.common.base.usecase.BaseUseCase
import com.space.quiz_api.GetQuestionsCount

class GetQuestionsCountUseCase(
    private val getQuestionsCount: GetQuestionsCount
) : BaseUseCase<String, Int>() {
    override suspend fun invoke(params: String?): Int {
        return getQuestionsCount(params!!)
    }
}