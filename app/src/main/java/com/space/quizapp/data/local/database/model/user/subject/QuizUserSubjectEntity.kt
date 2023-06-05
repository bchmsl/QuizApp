package com.space.quizapp.data.local.database.model.user.subject

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_subjects")
data class QuizUserSubjectEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val icon: String,
    val username: String,
    val score: Int
)
