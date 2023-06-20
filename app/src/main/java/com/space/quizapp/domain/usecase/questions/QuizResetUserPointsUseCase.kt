package com.space.quizapp.domain.usecase.questions

import com.space.quizapp.domain.repository.user.QuizUserSubjectRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase

class QuizResetUserPointsUseCase(
    private val userSubjectRepository: QuizUserSubjectRepository
) : QuizBaseUseCase<Unit, Unit>() {
    override suspend fun invoke(params: Unit?) {
        userSubjectRepository.resetUserPoints()
    }
}
