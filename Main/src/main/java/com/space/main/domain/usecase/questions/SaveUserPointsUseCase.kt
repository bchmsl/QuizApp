package com.space.main.domain.usecase.questions

import com.space.main.domain.repository.user.UserSubjectRepository
import com.space.main.domain.usecase.user.ReadUserDataUseCase
import com.space.quiz_api.SaveUserPoints

class SaveUserPointsUseCase(
    private val readUserDataUC: ReadUserDataUseCase,
    private val userSubjectRepository: UserSubjectRepository
) : SaveUserPoints {
    override suspend fun invoke(subjectTitle: String, points: Int) {
        userSubjectRepository.saveUserPoints(
            readUserDataUC().username, subjectTitle, points
        )
    }
}
