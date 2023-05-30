package com.space.quizapp.domain.model

data class QuizUserDomainModel(
    val username: String,
    var token: String = "",
    val gpa: Float
)
