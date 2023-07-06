package com.space.main_impl.domain.usecase.questions

import com.space.common.base.usecase.QuizBaseUseCase
import com.space.main_impl.domain.repository.user.QuizUserSubjectRepository
import com.space.main_impl.domain.usecase.user.QuizReadUserDataUseCase

class QuizSaveUserPointsUseCase(
    private val readUserDataUC: QuizReadUserDataUseCase,
    private val userSubjectRepository: QuizUserSubjectRepository
) : QuizBaseUseCase<QuizSaveUserPointsUseCase.SaveUserPointsParams, Unit>() {
    override suspend fun invoke(params: SaveUserPointsParams?) {
        userSubjectRepository.saveUserPoints(
            readUserDataUC().username, params!!.subjectTitle, params.points
        )
    }

    data class SaveUserPointsParams(val subjectTitle: String, val points: Int)
}
