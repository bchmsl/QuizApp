package com.space.quizapp.domain.model.quiz

data class QuizSubjectDomainModel(
    val id: Int,
    val quizTitle: String,
    val quizDescription: String,
    val quizIcon: String,
    val questionsCount: Int,
    val questions: List<QuizQuestionDomainModel>
) {
    data class QuizQuestionDomainModel(
        val questionTitle: String,
        val answers: List<String>,
        val correctAnswer: String,
        val questionIndex: Int
    )
}
