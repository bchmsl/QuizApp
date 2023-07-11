package com.space.common.model.subject.domain

data class SubjectDomainModel(
    val id: Int,
    val quizTitle: String,
    val quizDescription: String,
    val quizIcon: String,
    val questionsCount: Int,
)
