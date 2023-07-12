package com.space.common.model.question.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "questions")
@TypeConverters(ListStringConverter::class)
data class QuestionEntity(
    @PrimaryKey(autoGenerate = false)
    val questionTitle: String = "",
    val answers: List<String> = emptyList(),
    val correctAnswer: String = "",
    val subjectId: Int,
    val subjectTitle: String,
    val questionIndex: Int = -1,
    val isAnswered: Boolean,
    val isLastQuestion: Boolean,
    val points: Int
)
