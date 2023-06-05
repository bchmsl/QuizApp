package com.space.quizapp.domain.model.quiz

import com.space.quizapp.presentation.ui.ui_question.util.QuizAnswerSelectedState

data class QuizAnswerDomainModel(
    val answerOption: String,
    val selectedState: QuizAnswerSelectedState
    = QuizAnswerSelectedState.ANSWER_UNSELECTED
)
