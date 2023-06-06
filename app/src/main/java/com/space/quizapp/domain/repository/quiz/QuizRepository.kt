package com.space.quizapp.domain.repository.quiz

import com.space.quizapp.common.resource.QuizResource
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel

interface QuizRepository {
    suspend fun retrieveQuestions(): QuizResource<List<QuizSubjectDomainModel>>
    suspend fun updateLocalQuiz(
        subjects: List<QuizSubjectDomainModel>,
        questions: List<QuizQuestionDomainModel>
    )

    suspend fun getLocalSubjects(): List<QuizSubjectDomainModel>
}
