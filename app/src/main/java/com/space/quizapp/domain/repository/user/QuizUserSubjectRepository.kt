package com.space.quizapp.domain.repository.user

import com.space.quizapp.data.repository.BaseRepository
import com.space.quizapp.domain.model.user.QuizUserSubjectDomainModel

abstract class QuizUserSubjectRepository : BaseRepository() {
    abstract suspend fun insertUserSubject(userSubjectDomainModel: QuizUserSubjectDomainModel)
    abstract suspend fun retrieveUserSubjects(username: String): List<QuizUserSubjectDomainModel>
}
