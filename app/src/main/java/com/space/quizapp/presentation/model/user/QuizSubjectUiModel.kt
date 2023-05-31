package com.space.quizapp.presentation.model.user

data class QuizSubjectUiModel(
    val id: Int = 0,
    val userName: String,
    val title: String,
    val description: String,
    val icon: String,
    val score: Int
)
