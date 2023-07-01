package com.space.quizapp.domain.usecase.questions

import com.space.quizapp.domain.repository.user.QuizUserSubjectRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import com.space.quizapp.domain.usecase.user.QuizReadUserDataUseCase

class QuizSaveUserPointsUseCase(
    private val readUserDataUC: QuizReadUserDataUseCase,
    private val userSubjectRepository: QuizUserSubjectRepository
) : QuizBaseUseCase<SaveUserPointsRequest, Unit>() {
    override suspend fun invoke(params: SaveUserPointsRequest?) {
        userSubjectRepository.saveUserPoints(
            readUserDataUC().username,
            params!!.subjectTitle,
            params.points
        )
    }
}

data class SaveUserPointsRequest(val subjectTitle: String, val points: Int)
