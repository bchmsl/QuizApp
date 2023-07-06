package com.space.quizapp.domain.usecase.questions

import com.space.quizapp.domain.repository.user.QuizUserSubjectRepository
import com.space.quizapp.domain.usecase.user.QuizReadUserDataUseCase

class QuizSaveUserPointsUseCase(
    private val readUserDataUC: QuizReadUserDataUseCase,
    private val userSubjectRepository: QuizUserSubjectRepository
) : com.space.common.base.usecase.QuizBaseUseCase<QuizSaveUserPointsUseCase.SaveUserPointsParams, Unit>() {
    override suspend fun invoke(params: SaveUserPointsParams?) {
        userSubjectRepository.saveUserPoints(
            readUserDataUC().username, params!!.subjectTitle, params.points
        )
    }

    data class SaveUserPointsParams(val subjectTitle: String, val points: Int)
}
