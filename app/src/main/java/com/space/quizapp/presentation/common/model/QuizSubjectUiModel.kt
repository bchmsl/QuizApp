package com.space.quizapp.presentation.common.model

data class QuizSubjectUiModel(
    val id: Int = 0,
    val userName: String,
    val title: String,
    val description: String,
    val icon: String,
    val score: Int
)