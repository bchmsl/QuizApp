package com.space.quizapp.domain.usecase.questions

import com.space.quizapp.domain.repository.user.QuizUserSubjectRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase

class QuizGetPointsUseCase(
    private val userSubjectRepository: QuizUserSubjectRepository
) : QuizBaseUseCase<Unit, Int>() {

    override suspend fun invoke(params: Unit?): Int {
        return userSubjectRepository.getUserPoints()!!
    }
}
