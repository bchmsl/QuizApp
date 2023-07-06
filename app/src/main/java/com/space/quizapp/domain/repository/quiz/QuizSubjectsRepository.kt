package com.space.quizapp.domain.repository.quiz

import com.space.common.model.subject.domain.QuizSubjectDomainModel
import com.space.quizapp.data.repository.BaseRepository

abstract class QuizSubjectsRepository : BaseRepository() {
    abstract suspend fun retrieveSubjects(): List<com.space.common.model.subject.domain.QuizSubjectDomainModel>
    protected abstract suspend fun retrieveLocalSubjects(): List<com.space.common.model.subject.domain.QuizSubjectDomainModel>
    abstract suspend fun retrieveLocalSubjectByTitle(quizTitle: String): com.space.common.model.subject.domain.QuizSubjectDomainModel
}
