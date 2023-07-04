package com.space.quizapp.domain.model.quiz

import com.space.quizapp.presentation.ui.ui_question.util.QuizAnswerSelectedState

data class QuizQuestionDomainModel(
    val questionTitle: String,
    val answers: MutableList<QuizAnswerDomainModel>,
    val correctAnswer: QuizAnswerDomainModel,
    val subjectId: Int,
    val questionIndex: Int
) {
    data class QuizAnswerDomainModel(
        val answerOption: String,
        val selectedState: QuizAnswerSelectedState
        = QuizAnswerSelectedState.ANSWER_UNSELECTED
    )
}
