package com.space.quizapp.domain.model.user

data class QuizUserSubjectDomainModel(
    val quizTitle: String = "",
    var quizDescription: String = "",
    var quizIcon: String = "",
    val username: String = "",
    val score: Int,
    val questionsCount: Int
)
