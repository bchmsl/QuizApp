package com.space.quizapp.domain.repository.quiz

import com.space.quizapp.common.resource.QuizResource
import com.space.quizapp.data.repository.BaseRepository
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel

abstract class QuizRepository : BaseRepository() {
    abstract suspend fun retrieveQuestions(): QuizResource<List<QuizSubjectDomainModel>>
    abstract suspend fun updateLocalQuiz(
        subjects: List<QuizSubjectDomainModel>,
        questions: List<QuizQuestionDomainModel>
    )

    abstract suspend fun getLocalSubjects(): List<QuizSubjectDomainModel>
    abstract suspend fun getLocalQuestionsBySubject(
        username: String,
        subjectId: Int
    ): List<QuizQuestionDomainModel>
}
