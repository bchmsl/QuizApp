package com.space.quizapp.presentation.model.quiz

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizQuestionUiModel(
    val questionTitle: String,
    val answers: List<QuizAnswerUiModel>,
    val correctAnswer: QuizAnswerUiModel,
    val subjectId: Int,
    val questionIndex: Int,
    val isLastQuestion: Boolean,
    val isAnswered: Boolean,
    val subjectTitle: String
) : Parcelable {
    @Parcelize
    data class QuizAnswerUiModel(
        val answerOption: String,
        val isCorrect: Boolean
    ) : Parcelable
}
