package com.space.quizapp.presentation.model.quiz

import android.os.Parcelable
import com.space.quizapp.presentation.ui.ui_question.util.QuizAnswerSelectedState
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizAnswerUiModel(
    val answerOption: String,
    val selectedState: QuizAnswerSelectedState
    = QuizAnswerSelectedState.ANSWER_UNSELECTED
) : Parcelable
