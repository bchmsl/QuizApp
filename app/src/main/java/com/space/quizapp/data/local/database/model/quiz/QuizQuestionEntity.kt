package com.space.quizapp.data.local.database.model.quiz

import androidx.room.Entity

@Entity(tableName = "questions")
data class QuizQuestionEntity(
    val questionTitle: String = "",
    val answers: List<String> = emptyList(),
    val correctAnswer: String = "",
    val subjectId: Int,
    val questionIndex: Int = -1
)
