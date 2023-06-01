package com.space.quizapp.presentation.ui.ui_question.model

import com.space.quizapp.presentation.ui.ui_question.util.QuizAnswerSelectedState

data class AnswerModel(
    val answerOption: String,
    val selectedState: QuizAnswerSelectedState = QuizAnswerSelectedState.ANSWER_UNSELECTED
)
