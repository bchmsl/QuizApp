package com.space.quizapp.data.local.database.model.quiz.subject

import androidx.room.Entity
import com.space.quizapp.data.remote.model.QuizQuestionsDto

@Entity(tableName = "subjects")
data class QuizEntity(
    val id: Int = 0,
    val quizTitle: String = "",
    val quizDescription: String = "",
    val quizIcon: String = "",
    val questionsCount: Int = 0,
    val questions: List<QuizQuestionsDto.Question> = emptyList()
)
