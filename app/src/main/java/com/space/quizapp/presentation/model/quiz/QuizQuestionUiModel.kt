package com.space.quizapp.presentation.model.quiz

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizQuestionUiModel(
    val questionTitle: String,
    val answers: List<QuizAnswerUiModel>,
    val correctAnswer: QuizAnswerUiModel,
    val subjectId: Int,
    val questionIndex: Int
) : Parcelable
