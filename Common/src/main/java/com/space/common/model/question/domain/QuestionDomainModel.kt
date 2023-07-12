package com.space.common.model.question.domain

import com.space.common.model.question.model.AnswerSelectedState

data class QuestionDomainModel(
    val questionTitle: String,
    val answers: MutableList<AnswerDomainModel>,
    val correctAnswer: AnswerDomainModel,
    val subjectId: Int,
    val questionIndex: Int,
    val isAnswered: Boolean,
    val isLastQuestion: Boolean,
    val subjectTitle: String,
    val points: Int
) {
    data class AnswerDomainModel(
        val answerOption: String,
        val isCorrect: Boolean,
        var answerSelectedState: AnswerSelectedState
        = AnswerSelectedState.ANSWER_NEUTRAL
    )
}
