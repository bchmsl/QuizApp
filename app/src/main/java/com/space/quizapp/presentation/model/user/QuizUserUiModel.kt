package com.space.quizapp.presentation.model.user

data class QuizUserUiModel(
    val userName: String,
    val gpa: Float = 0f,
    val subjects: List<QuizUserSubjectUiModel> = emptyList()
)
