package com.space.quizapp.data.local.database.model.quiz.subject

import androidx.room.Entity

@Entity(tableName = "subjects")
data class QuizSubjectEntity(
    val id: Int = 0,
    val quizTitle: String = "",
    val quizDescription: String = "",
    val quizIcon: String = "",
    val questionsCount: Int = 0
)
