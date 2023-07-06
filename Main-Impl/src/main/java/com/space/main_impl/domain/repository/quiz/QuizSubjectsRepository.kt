package com.space.main_impl.domain.repository.quiz

import com.space.common.model.subject.domain.QuizSubjectDomainModel

abstract class QuizSubjectsRepository {
    abstract suspend fun retrieveSubjects(): List<QuizSubjectDomainModel>
    protected abstract suspend fun retrieveLocalSubjects(): List<QuizSubjectDomainModel>
    abstract suspend fun retrieveLocalSubjectByTitle(quizTitle: String): QuizSubjectDomainModel
}
