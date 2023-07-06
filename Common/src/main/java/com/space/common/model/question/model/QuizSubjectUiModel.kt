package com.space.common.model.question.model

data class QuizSubjectUiModel(
    val id: Int,
    val quizTitle: String,
    val quizDescription: String,
    val quizIcon: String,
    val questionsCount: Int,
)
