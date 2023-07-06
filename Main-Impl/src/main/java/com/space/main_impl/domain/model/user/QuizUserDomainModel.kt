package com.space.main_impl.domain.model.user

data class QuizUserDomainModel(
    val username: String,
    var token: String = "",
    val gpa: Float
)
