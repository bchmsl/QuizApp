package com.space.quizapp.domain.usecase.questions

import com.space.quizapp.domain.repository.user.QuizUserSubjectRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import com.space.quizapp.domain.usecase.user.read_user_data.QuizReadUserDataUseCase

class QuizSaveUserPointsUseCase(
    private val readUserDataUseCase: QuizReadUserDataUseCase,
    private val userSubjectRepository: QuizUserSubjectRepository
) : QuizBaseUseCase<String, Unit>() {
    override suspend fun invoke(params: String?) {
        userSubjectRepository.saveUserPoints(readUserDataUseCase().username, params!!)
    }
}
