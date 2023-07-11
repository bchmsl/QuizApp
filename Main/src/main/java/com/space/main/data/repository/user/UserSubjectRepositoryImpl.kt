package com.space.main.data.repository.user

import android.util.Log
import com.space.main.data.local.database.dao.QuestionsDao
import com.space.main.data.local.database.dao.SubjectsDao
import com.space.main.data.local.database.dao.UserSubjectsDao
import com.space.main.data.local.database.model.user.UserSubjectEntity
import com.space.main.data.local.database.model.user.mapper.UserSubjectEntityMapper
import com.space.main.domain.model.user.UserSubjectDomainModel
import com.space.main.domain.repository.user.UserSubjectRepository

class UserSubjectRepositoryImpl(
    private val userSubjectsDao: UserSubjectsDao,
    private val subjectsDao: SubjectsDao,
    private val questionsDao: QuestionsDao,
    private val userSubjectEntityMapper: UserSubjectEntityMapper
) : UserSubjectRepository() {

    override suspend fun retrieveUserSubjects(username: String): List<UserSubjectDomainModel> {
        return userSubjectsDao.getUserSubjects(username)
            .map { userSubjectEntityMapper.toDomain(it) }
    }

    override suspend fun getUserSubject(
        username: String,
        quizTitle: String
    ): UserSubjectEntity? {
        return userSubjectsDao.getUserSubjectByTitleIfExists(username, quizTitle)
    }

    override suspend fun updateOrInsertUserSubject(
        userSubjectEntity: UserSubjectEntity
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
            UserSubjectEntity(
                username = username,
                quizTitle = quizTitle,
                score = points,
                questionsCount = subject.questionsCount,
                maxScore = questionsSum
            )
        )
    }
}
