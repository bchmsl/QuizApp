package com.space.quizapp.data.local.database.model.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_subject")
data class QuizUserSubjectEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val subjectId: Int,
    val score: Int
)
