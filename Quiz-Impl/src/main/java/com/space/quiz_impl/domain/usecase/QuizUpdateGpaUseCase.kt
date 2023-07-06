package com.space.quiz_impl.domain.usecase

import com.space.common.base.usecase.QuizBaseUseCase
import com.space.quiz_api.UpdateUserGpa

class QuizUpdateGpaUseCase(
    private val updateUserGpa: UpdateUserGpa
) : QuizBaseUseCase<Unit, Unit>() {
    override suspend fun invoke(params: Unit?) {
        updateUserGpa()
    }
}
