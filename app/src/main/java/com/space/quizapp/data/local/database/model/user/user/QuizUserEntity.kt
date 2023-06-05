package com.space.quizapp.data.local.database.model.user.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class QuizUserEntity(
    @PrimaryKey
    val username: String,
    val token: String,
    val gpa: Float
)
