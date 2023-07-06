package com.space.quiz_impl.presentation.quiz.model

data class QuizQuestionUiModel(
    val questionTitle: String,
    val answers: List<QuizAnswerUiModel>,
    val correctAnswer: QuizAnswerUiModel,
    val subjectId: Int,
    val questionIndex: Int,
    val isLastQuestion: Boolean,
    val isAnswered: Boolean,
    val subjectTitle: String,
    val points: Int
) {
    data class QuizAnswerUiModel(
        val answerOption: String,
        val isCorrect: Boolean,
        var answerSelectedState: QuizAnswerSelectedState
    )
}
