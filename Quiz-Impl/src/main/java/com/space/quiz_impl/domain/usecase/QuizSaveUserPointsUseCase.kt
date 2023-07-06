package com.space.quiz_impl.domain.usecase

import com.space.common.base.usecase.QuizBaseUseCase
import com.space.quiz_api.SaveUserPoints

class QuizSaveUserPointsUseCase(
    private val saveUserPoints: SaveUserPoints
) : QuizBaseUseCase<QuizSaveUserPointsUseCase.SaveUserPointsParams, Unit>() {
    override suspend fun invoke(params: SaveUserPointsParams?) {
        saveUserPoints(params!!.subjectTitle, params.points)
    }

    data class SaveUserPointsParams(val subjectTitle: String, val points: Int)
}
