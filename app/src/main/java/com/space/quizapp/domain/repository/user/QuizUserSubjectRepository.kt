package com.space.quizapp.domain.repository.user

import com.space.quizapp.data.local.database.model.user.QuizUserSubjectEntity
import com.space.quizapp.data.repository.BaseRepository
import com.space.quizapp.domain.model.user.QuizUserSubjectDomainModel
import java.util.concurrent.atomic.AtomicReference

abstract class QuizUserSubjectRepository : BaseRepository() {
    abstract val userPoints: AtomicReference<Int>
    abstract suspend fun insertUserSubject(userSubjectDomainModel: QuizUserSubjectDomainModel)
    abstract suspend fun retrieveUserSubjects(username: String): List<QuizUserSubjectDomainModel>
    abstract suspend fun updateOrInsertUserSubject(
        username: String,
        userSubjectEntity: QuizUserSubjectEntity
    )

    abstract suspend fun getUserSubject(username: String, quizTitle: String): QuizUserSubjectEntity?
    abstract suspend fun saveUserPoints(username: String, quizTitle: String)
    abstract suspend fun addPoint(points: Int)
    abstract suspend fun getUserPoints(): Int?
    abstract suspend fun resetUserPoints()
}
