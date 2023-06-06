package com.space.quizapp.domain.model.quiz

data class QuizSubjectDomainModel(
    val id: Int,
    val quizTitle: String,
    val quizDescription: String,
    val quizIcon: String,
    val questionsCount: Int,
)
