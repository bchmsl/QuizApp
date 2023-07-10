package com.space.main.domain.repository.quiz

import com.space.common.model.question.domain.QuizQuestionDomainModel

abstract class QuizQuestionsRepository {
    abstract suspend fun getNextQuestion(subjectTitle: String): QuizQuestionDomainModel?
    abstract suspend fun updateQuestion(question: QuizQuestionDomainModel)
    abstract suspend fun resetAnsweredStates(subjectTitle: String)
    abstract suspend fun getQuestionsCount(subjectTitle: String): Int
    abstract suspend fun getQuestionsBySubjectTitle(subjectTitle: String): List<QuizQuestionDomainModel>
}
