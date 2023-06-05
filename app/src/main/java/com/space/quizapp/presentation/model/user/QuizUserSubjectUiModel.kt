package com.space.quizapp.presentation.model.user

data class QuizUserSubjectUiModel(
    val id: Int = 0,
    val title: String,
    val description: String,
    val icon: String,
    val score: Int,
    val username: String
)
