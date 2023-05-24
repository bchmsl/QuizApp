package com.space.quizapp.presentation.common.model

data class UserUiModel(
    val userName: String,
    val gpa: Float = 0f,
    val subjects: List<SubjectUiModel> = emptyList()
)
