package com.space.quizapp.domain.repository.quiz

import com.space.quizapp.data.repository.BaseRepository
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel

abstract class QuizQuestionsRepository : BaseRepository() {
    abstract suspend fun getNextQuestion(subjectTitle: String): QuizQuestionDomainModel?
    abstract suspend fun updateQuestion(question: QuizQuestionDomainModel)
    abstract suspend fun resetAnsweredStates(subjectTitle: String)
    abstract suspend fun getQuestionsCount(subjectTitle: String): Int
    abstract suspend fun getQuestionsBySubjectTitle(subjectTitle: String): List<QuizQuestionDomainModel>
}
