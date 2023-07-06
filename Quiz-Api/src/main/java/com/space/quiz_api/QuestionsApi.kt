package com.space.quiz_api

import com.space.common.model.question.data.QuizQuestionEntity

interface QuestionsApi {
    fun getNextQuestion(subjectTitle: String): QuizQuestionEntity?
    fun getQuestionsCount(subjectTitle: String): Int
    fun updateQuestion(questionEntity: QuizQuestionEntity)

}