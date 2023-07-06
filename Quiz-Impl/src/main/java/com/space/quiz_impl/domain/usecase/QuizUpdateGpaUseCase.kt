package com.space.quiz_impl.domain.usecase

import com.space.quiz_api.UserApi

class QuizUpdateGpaUseCase(
    private val userApi: UserApi
) : com.space.common.base.usecase.QuizBaseUseCase<Unit, Unit>() {
    override suspend fun invoke(params: Unit?) {
        userApi.updateUserGpa()
    }
}
