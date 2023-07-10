package com.space.main.domain.model.user

data class QuizUserDomainModel(
    val username: String,
    var token: String = "",
    val gpa: Float
)
