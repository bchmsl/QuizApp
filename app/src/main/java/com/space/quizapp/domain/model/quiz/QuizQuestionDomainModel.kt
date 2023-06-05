package com.space.quizapp.domain.model.quiz

data class QuizQuestionDomainModel(
    val questionTitle: String,
    val answers: List<QuizAnswerDomainModel>,
    val correctAnswer: QuizAnswerDomainModel,
    val questionIndex: Int
)
