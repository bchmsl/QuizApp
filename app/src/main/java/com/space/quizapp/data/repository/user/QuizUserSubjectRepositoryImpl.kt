package com.space.quizapp.data.repository.user

import com.space.quizapp.data.local.database.dao.QuizUserSubjectsDao
import com.space.quizapp.data.local.database.model.user.QuizUserSubjectEntity
import com.space.quizapp.data.local.database.model.user.mapper.QuizUserSubjectEntityMapper
import com.space.quizapp.domain.model.user.QuizUserSubjectDomainModel
import com.space.quizapp.domain.repository.user.QuizUserSubjectRepository

class QuizUserSubjectRepositoryImpl(
    private val userSubjectsDao: QuizUserSubjectsDao,
    private val userSubjectEntityMapper: QuizUserSubjectEntityMapper
) : QuizUserSubjectRepository() {

    override suspend fun retrieveUserSubjects(username: String): List<QuizUserSubjectDomainModel> {
        return userSubjectsDao.getUserSubjects(username)
            .map { userSubjectEntityMapper.toDomain(it) }
    }

    override suspend fun getUserSubject(
        username: String,
        quizTitle: String
    ): QuizUserSubjectEntity? {
        return userSubjectsDao.getUserSubjectByTitleIfExists(username, quizTitle)
    }

    override suspend fun updateOrInsertUserSubject(
        userSubjectEntity: QuizUserSubjectEntity
    ) {
        val savedSubject = userSubjectsDao.getUserSubjectByTitleIfExists(
            userSubjectEntity.username,
            userSubjectEntity.quizTitle
        )
        savedSubject?.let {
            userSubjectsDao.updateUserSubject(it)
            return
        }
        userSubjectsDao.insertUserSubject(userSubjectEntity)
    }

    override suspend fun saveUserPoints(username: String, quizTitle: String, points: Int) {
        var userSubject = getUserSubject(username, quizTitle)
        userSubject?.let {
            if (it.score >= points) return
            userSubject = it.copy(score = points)
            updateOrInsertUserSubject(userSubject!!)
            return
        }
        updateOrInsertUserSubject(
            QuizUserSubjectEntity(
                username = username,
                quizTitle = quizTitle,
                score = points
            )
        )
    }
}
