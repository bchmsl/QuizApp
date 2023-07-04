package com.space.quizapp.domain.usecase.questions

import com.space.quizapp.domain.repository.user.QuizUserSubjectRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase

class AddPointsToSubjectUseCase(
    private val userSubjectRepository: QuizUserSubjectRepository
) : QuizBaseUseCase<AddPointsParams, Unit>() {
    override suspend fun invoke(params: AddPointsParams?) {
        userSubjectRepository.addPoint(params!!.points)
    }
}

data class AddPointsParams(val userSubject: String, val points: Int)
