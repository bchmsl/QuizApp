package com.space.main.domain.model.user

data class UserDomainModel(
    val username: String,
    var token: String = "",
    val gpa: Float
)
