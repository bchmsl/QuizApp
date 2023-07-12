package com.space.main.presentation.model.user

data class UserSubjectUiModel(
    val quizTitle: String = "",
    val quizDescription: String = "",
    val quizIcon: String = "",
    val score: Int,
    val questionsCount: Int,
    val maxScore: Int
)
