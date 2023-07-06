package com.space.quiz_impl.domain.model

import com.space.quiz_impl.presentation.quiz.model.QuizAnswerSelectedState

data class QuizQuestionDomainModel(
    val questionTitle: String,
    val answers: MutableList<QuizAnswerDomainModel>,
    val correctAnswer: QuizAnswerDomainModel,
    val subjectId: Int,
    val questionIndex: Int,
    val isAnswered: Boolean,
    val isLastQuestion: Boolean,
    val subjectTitle: String,
    val points: Int
) {
    data class QuizAnswerDomainModel(
        val answerOption: String,
        val isCorrect: Boolean,
        var answerSelectedState: QuizAnswerSelectedState
        = QuizAnswerSelectedState.ANSWER_NEUTRAL
    )
}
