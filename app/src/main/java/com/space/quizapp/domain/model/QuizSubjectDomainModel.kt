package com.space.quizapp.domain.model

data class QuizSubjectDomainModel(
    val id: Int,
    val username: String,
    val title: String,
    val description: String,
    val icon: String,
    val score: Int
)
