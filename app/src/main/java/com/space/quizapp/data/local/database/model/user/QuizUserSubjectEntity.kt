package com.space.quizapp.data.local.database.model.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_subject")
data class QuizUserSubjectEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val quizTitle: String,
    val score: Int
)
