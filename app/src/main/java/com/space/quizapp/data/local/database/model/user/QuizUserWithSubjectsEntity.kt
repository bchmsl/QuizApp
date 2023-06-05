package com.space.quizapp.data.local.database.model.user

import com.space.quizapp.data.local.database.model.user.subject.QuizUserSubjectEntity

data class QuizUserWithSubjectsEntity(
    val username: String,
    val token: String,
    val gpa: Float,
    val subjects: List<QuizUserSubjectEntity>
)
