package com.space.quizapp.domain.model.quiz

data class QuizQuestionsDomainModel(
    val id: Int,
    val quizTitle: String,
    val quizDescription: String,
    val quizIcon: String,
    val questionsCount: Int,
    val questions: List<Question>
) {
    data class Question(
        val questionTitle: String,
        val answers: List<String>,
        val correctAnswer: String,
        val questionIndex: Int
    )
}
