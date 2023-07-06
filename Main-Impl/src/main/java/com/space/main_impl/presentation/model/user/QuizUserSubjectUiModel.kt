package com.space.main_impl.presentation.model.user

data class QuizUserSubjectUiModel(
    val quizTitle: String = "",
    val quizDescription: String = "",
    val quizIcon: String = "",
    val score: Int,
    val questionsCount: Int,
    val maxScore: Int
)
