package com.space.main.domain.model.user

data class UserSubjectDomainModel(
    val quizTitle: String = "",
    var quizDescription: String = "",
    var quizIcon: String = "",
    val username: String = "",
    val score: Int,
    val questionsCount: Int,
    val maxScore: Int
)
