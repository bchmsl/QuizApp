package com.space.quizapp.domain.repository.user

import com.space.quizapp.data.local.database.model.user.QuizUserSubjectEntity
import com.space.quizapp.data.repository.BaseRepository
import com.space.quizapp.domain.model.user.QuizUserSubjectDomainModel

abstract class QuizUserSubjectRepository : BaseRepository() {
    abstract suspend fun insertUserSubject(userSubjectDomainModel: QuizUserSubjectDomainModel)
    abstract suspend fun retrieveUserSubjects(username: String): List<QuizUserSubjectDomainModel>
    abstract suspend fun updateOrInsertUserSubject(
        username: String,
        userSubjectEntity: QuizUserSubjectEntity
    )

    abstract suspend fun getUserSubject(username: String, quizTitle: String): QuizUserSubjectEntity?
    abstract suspend fun saveUserPoints(username: String, quizTitle: String, points: Int)
}
