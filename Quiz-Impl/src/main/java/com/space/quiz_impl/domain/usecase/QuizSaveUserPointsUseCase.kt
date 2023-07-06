package com.space.quiz_impl.domain.usecase

import com.space.quiz_api.UserApi

class QuizSaveUserPointsUseCase(
    private val userApi: UserApi
) : com.space.common.base.usecase.QuizBaseUseCase<QuizSaveUserPointsUseCase.SaveUserPointsParams, Unit>() {
    override suspend fun invoke(params: SaveUserPointsParams?) {
        userApi.saveUserPoints(params!!.subjectTitle, params.points)
    }

    data class SaveUserPointsParams(val subjectTitle: String, val points: Int)
}
