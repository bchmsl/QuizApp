package com.space.quizapp.domain.model.user

data class QuizUserDomainModel(
    val username: String,
    var token: String = "",
    val gpa: Float,
    val subjects: List<QuizUserSubjectDomainModel>
)
