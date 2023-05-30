package com.space.quizapp.presentation.model

data class QuizUserUiModel(
    val userName: String,
    val gpa: Float = 0f,
    val subjects: List<QuizSubjectUiModel> = emptyList()
)
