package com.space.quizapp.domain.model.user

data class QuizUserSubjectDomainModel(
    val id: Int,
    val title: String,
    val description: String,
    val icon: String,
    val score: Int,
    val username: String,
)
