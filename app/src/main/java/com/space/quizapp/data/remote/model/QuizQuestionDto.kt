package com.space.quizapp.data.remote.model

data class QuizQuestionDto(
    val questionTitle: String = "",
    val answers: List<String> = emptyList(),
    val correctAnswer: String = "",
    val questionIndex: Int = -1
)
