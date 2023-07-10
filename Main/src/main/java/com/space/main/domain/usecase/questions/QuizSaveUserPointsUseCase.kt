package com.space.main.domain.usecase.questions

import com.space.main.domain.repository.user.QuizUserSubjectRepository
import com.space.main.domain.usecase.user.QuizReadUserDataUseCase
import com.space.quiz_api.SaveUserPoints

class QuizSaveUserPointsUseCase(
    private val readUserDataUC: QuizReadUserDataUseCase,
    private val userSubjectRepository: QuizUserSubjectRepository
) : SaveUserPoints {
    override suspend fun invoke(subjectTitle: String, points: Int) {
        userSubjectRepository.saveUserPoints(
            readUserDataUC().username, subjectTitle, points
        )
    }
}
