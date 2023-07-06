package com.space.quiz_impl.domain.repository

import com.space.quiz_impl.domain.model.QuizQuestionDomainModel

interface QuizRepository {
    fun getNextQuestion(subjectTitle: String): QuizQuestionDomainModel?
    fun getQuestionsCount(subjectTitle: String): Int
    fun updateQuestion(questionDomainModel: QuizQuestionDomainModel)

}
