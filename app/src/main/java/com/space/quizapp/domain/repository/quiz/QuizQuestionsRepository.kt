package com.space.quizapp.domain.repository.quiz

import com.space.common.model.question.domain.QuizQuestionDomainModel
import com.space.quizapp.data.repository.BaseRepository

abstract class QuizQuestionsRepository : BaseRepository() {
    abstract suspend fun getNextQuestion(subjectTitle: String): com.space.common.model.question.domain.QuizQuestionDomainModel?
    abstract suspend fun updateQuestion(question: com.space.common.model.question.domain.QuizQuestionDomainModel)
    abstract suspend fun resetAnsweredStates(subjectTitle: String)
    abstract suspend fun getQuestionsCount(subjectTitle: String): Int
    abstract suspend fun getQuestionsBySubjectTitle(subjectTitle: String): List<com.space.common.model.question.domain.QuizQuestionDomainModel>
}
