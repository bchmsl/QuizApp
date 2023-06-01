package com.space.quizapp.presentation.model.quiz

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizQuestionsUiModel(
    val id: Int,
    val quizTitle: String,
    val quizDescription: String,
    val quizIcon: String,
    val questionsCount: Int,
    val questions: List<Question>
) : Parcelable {

    @Parcelize
    data class Question(
        val questionTitle: String,
        val answers: List<String>,
        val correctAnswer: String,
        val questionIndex: Int
    ) : Parcelable
}
