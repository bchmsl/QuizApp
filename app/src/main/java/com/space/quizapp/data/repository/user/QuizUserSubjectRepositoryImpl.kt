package com.space.quizapp.data.repository.user

import com.space.quizapp.data.local.database.dao.QuizUserSubjectsDao
import com.space.quizapp.data.local.database.model.user.mapper.QuizUserSubjectEntityMapper
import com.space.quizapp.domain.model.user.QuizUserSubjectDomainModel
import com.space.quizapp.domain.repository.user.QuizUserSubjectRepository

class QuizUserSubjectRepositoryImpl(
    private val userSubjectsDao: QuizUserSubjectsDao,
    private val userSubjectEntityMapper: QuizUserSubjectEntityMapper
) : QuizUserSubjectRepository() {
    override suspend fun insertUserSubject(userSubjectDomainModel: QuizUserSubjectDomainModel) {
        val userSubjectEntity = userSubjectEntityMapper.toEntity(userSubjectDomainModel)
        val oldSubject = userSubjectsDao.getUserSubjectByIdIfExists(
            userSubjectEntity.username,
            userSubjectEntity.subjectId
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
}
