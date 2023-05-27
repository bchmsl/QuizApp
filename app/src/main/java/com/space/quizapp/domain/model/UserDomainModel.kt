package com.space.quizapp.domain.model

data class UserDomainModel(
    val username: String,
    var token: String = "",
    val gpa: Float
)
