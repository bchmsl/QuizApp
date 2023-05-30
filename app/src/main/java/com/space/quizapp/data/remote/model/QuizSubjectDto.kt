package com.space.quizapp.data.remote.model

data class QuizSubjectDto(
    val id: Int = 0,
    val quizTitle: String = "",
    val quizDescription: String = "",
    val quizIcon: String = "",
    val questionsCount: Int = 0,
    val questions: List<Question> = emptyList()
) {
    data class Question(
        val questionTitle: String = "",
        val answers: List<String> = emptyList(),
        val correctAnswer: String = "",
        val questionIndex: Int = 0
    )
}
