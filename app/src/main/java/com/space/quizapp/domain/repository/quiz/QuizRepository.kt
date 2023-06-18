package com.space.quizapp.domain.repository.quiz

import com.space.quizapp.data.repository.BaseRepository
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel

abstract class QuizRepository : BaseRepository() {
    abstract suspend fun retrieveSubjects(): List<QuizSubjectDomainModel>
    protected abstract suspend fun retrieveLocalSubjects(): List<QuizSubjectDomainModel>
    abstract suspend fun getLocalQuestionsBySubjectId(
        subjectId: Int
    ): List<QuizQuestionDomainModel>
}
