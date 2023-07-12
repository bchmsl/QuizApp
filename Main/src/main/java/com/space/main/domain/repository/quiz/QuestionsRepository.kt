package com.space.main.domain.repository.quiz

import com.space.common.model.question.domain.QuestionDomainModel

abstract class QuestionsRepository {
    abstract suspend fun getNextQuestion(subjectTitle: String): QuestionDomainModel?
    abstract suspend fun updateQuestion(question: QuestionDomainModel)
    abstract suspend fun resetAnsweredStates(subjectTitle: String)
    abstract suspend fun getQuestionsCount(subjectTitle: String): Int
    abstract suspend fun getQuestionsBySubjectTitle(subjectTitle: String): List<QuestionDomainModel>
}
