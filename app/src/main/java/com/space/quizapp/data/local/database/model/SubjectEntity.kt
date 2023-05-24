package com.space.quizapp.data.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class SubjectEntity(
    @PrimaryKey
    val id: Int,
    val username: String,
    val title: String,
    val description: String,
    val icon: String,
    val score: Int
)
