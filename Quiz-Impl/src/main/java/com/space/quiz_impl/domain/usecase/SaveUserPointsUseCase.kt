package com.space.quiz_impl.domain.usecase

import com.space.common.base.usecase.BaseUseCase
import com.space.quiz_api.SaveUserPoints

class SaveUserPointsUseCase(
    private val saveUserPoints: SaveUserPoints
) : BaseUseCase<SaveUserPointsUseCase.SaveUserPointsParams, Unit>() {
    override suspend fun invoke(params: SaveUserPointsParams?) {
        saveUserPoints(params!!.subjectTitle, params.points)
    }

    data class SaveUserPointsParams(val subjectTitle: String, val points: Int)
}
