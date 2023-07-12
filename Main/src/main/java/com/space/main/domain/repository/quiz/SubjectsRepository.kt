package com.space.main.domain.repository.quiz

import com.space.common.model.subject.domain.SubjectDomainModel

abstract class SubjectsRepository {
    abstract suspend fun retrieveSubjects(): List<SubjectDomainModel>
    protected abstract suspend fun retrieveLocalSubjects(): List<SubjectDomainModel>
    abstract suspend fun retrieveLocalSubjectByTitle(quizTitle: String): SubjectDomainModel
}
