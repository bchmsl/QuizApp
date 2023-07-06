package com.space.main_impl.domain.repository.user

import com.space.main_impl.data.local.database.model.user.QuizUserSubjectEntity
import com.space.main_impl.domain.model.user.QuizUserSubjectDomainModel

abstract class QuizUserSubjectRepository {
    abstract suspend fun retrieveUserSubjects(username: String): List<QuizUserSubjectDomainModel>
    abstract suspend fun updateOrInsertUserSubject(
        userSubjectEntity: QuizUserSubjectEntity
    )

    abstract suspend fun getUserSubject(username: String, quizTitle: String): QuizUserSubjectEntity?
    abstract suspend fun saveUserPoints(username: String, quizTitle: String, points: Int)
}
