package com.space.quizapp.domain.model.quiz

data class QuizQuestionDomainModel(
    val questionTitle: String,
    val answers: MutableList<QuizAnswerDomainModel>,
    val correctAnswer: QuizAnswerDomainModel,
    val subjectId: Int,
    val questionIndex: Int,
    val isAnswered: Boolean,
    val isLastQuestion: Boolean,
    val subjectTitle: String
) {
    data class QuizAnswerDomainModel(
        val answerOption: String,
        val isCorrect: Boolean
    )
}
