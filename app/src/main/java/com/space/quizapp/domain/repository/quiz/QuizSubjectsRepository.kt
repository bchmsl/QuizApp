package com.space.quizapp.domain.repository.quiz

import com.space.quizapp.data.repository.BaseRepository
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel

abstract class QuizSubjectsRepository : BaseRepository() {
    abstract suspend fun retrieveSubjects(): List<QuizSubjectDomainModel>
    protected abstract suspend fun retrieveLocalSubjects(): List<QuizSubjectDomainModel>
    abstract suspend fun retrieveLocalSubjectByTitle(quizTitle: String): QuizSubjectDomainModel
}
