package com.space.quizapp.data.repository.user

import com.space.quizapp.data.local.database.dao.QuizUserSubjectsDao
import com.space.quizapp.data.local.database.model.user.QuizUserSubjectEntity
import com.space.quizapp.data.local.database.model.user.mapper.QuizUserSubjectEntityMapper
import com.space.quizapp.domain.model.user.QuizUserSubjectDomainModel
import com.space.quizapp.domain.repository.user.QuizUserSubjectRepository
import java.util.concurrent.atomic.AtomicReference

class QuizUserSubjectRepositoryImpl(
    private val userSubjectsDao: QuizUserSubjectsDao,
    private val userSubjectEntityMapper: QuizUserSubjectEntityMapper
) : QuizUserSubjectRepository() {


    override val userPoints = AtomicReference(0)

    override suspend fun insertUserSubject(userSubjectDomainModel: QuizUserSubjectDomainModel) {
        val userSubjectEntity = userSubjectEntityMapper.toEntity(userSubjectDomainModel)
        val oldSubject = userSubjectsDao.getUserSubjectByTitleIfExists(
            userSubjectEntity.username,
            userSubjectEntity.quizTitle
        )
        if (oldSubject == null) {
            userSubjectsDao.insertUserSubject(userSubjectEntity)
            return
        }
        if (oldSubject.score >= userSubjectEntity.score) return
        userSubjectsDao.deleteUserSubject(oldSubject)
        userSubjectsDao.insertUserSubject(userSubjectEntity)
    }

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
        username: String,
        userSubjectEntity: QuizUserSubjectEntity
    ) {
        val savedSubject = userSubjectsDao.getUserSubjectByTitleIfExists(
            username,
            userSubjectEntity.quizTitle
        )
        savedSubject?.let {
            userSubjectsDao.updateUserSubject(
                userSubjectEntity
            )
            return
        }
        userSubjectsDao.insertUserSubject(userSubjectEntity)
    }

    override suspend fun addPoint(points: Int) {
        userPoints.set(userPoints.get() + points)
    }

    override suspend fun saveUserPoints(username: String, quizTitle: String) {
        val subject = getUserSubject(username, quizTitle)
        subject?.let {
            if (it.score >= userPoints.get()) return
            val user = subject.copy(score = userPoints.get())
            updateOrInsertUserSubject(username, user)
            return
        }
        userSubjectsDao.insertUserSubject(
            QuizUserSubjectEntity(
                username = username,
                quizTitle = quizTitle,
                score = userPoints.get()
            )
        )
        resetUserPoints()
    }

    override suspend fun getUserPoints(): Int {
        return userPoints.get()
    }

    override suspend fun resetUserPoints() {
        userPoints.set(0)
    }
}
