package com.space.quizapp.data.local.database.model.quiz.questions

import androidx.room.Entity

@Entity(tableName = "questions")
data class QuizQuestionEntity(
    val questionTitle: String = "",
    val answers: List<String> = emptyList(),
    val correctAnswer: String = "",
    val questionIndex: Int = -1
)