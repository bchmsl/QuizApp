package com.space.quizapp.data.repository.user

import android.util.Log
import com.space.quizapp.data.local.database.dao.QuizQuestionsDao
import com.space.quizapp.data.local.database.dao.QuizSubjectsDao
import com.space.quizapp.data.local.database.dao.QuizUserSubjectsDao
import com.space.quizapp.data.local.database.model.user.QuizUserSubjectEntity
import com.space.quizapp.data.local.database.model.user.mapper.QuizUserSubjectEntityMapper
import com.space.quizapp.domain.model.user.QuizUserSubjectDomainModel
import com.space.quizapp.domain.repository.user.QuizUserSubjectRepository

class QuizUserSubjectRepositoryImpl(
    private val userSubjectsDao: QuizUserSubjectsDao,
    private val subjectsDao: QuizSubjectsDao,
    private val questionsDao: QuizQuestionsDao,
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
        Log.d("TAG_REPO", savedSubject.toString())
        savedSubject?.let {
            userSubjectsDao.updateUserSubject(it.copy(score = userSubjectEntity.score))
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
        val subject = subjectsDao.getSubjectByTitle(quizTitle)
        val questionsSum = questionsDao.getAllQuestions(subject.quizTitle).sumOf { it.points }
        updateOrInsertUserSubject(
            QuizUserSubjectEntity(
                username = username,
                quizTitle = quizTitle,
                score = points,
                questionsCount = subject.questionsCount,
                maxScore = questionsSum
            )
        )
    }
}
