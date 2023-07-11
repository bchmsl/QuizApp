package com.space.main.data.remote.model

data class SubjectDto(
    val id: Int = 0,
    val quizTitle: String = "",
    val quizDescription: String = "",
    val quizIcon: String = "",
    val questionsCount: Int = 0,
    val questions: List<QuizQuestionDto> = emptyList()
) {
    data class QuizQuestionDto(
        val questionTitle: String = "",
        val answers: List<String> = emptyList(),
        val correctAnswer: String = "",
        val subjectId: Int,
        val questionIndex: Int = -1
    )
}
