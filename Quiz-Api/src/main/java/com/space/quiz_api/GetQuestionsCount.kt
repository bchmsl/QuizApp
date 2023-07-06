package com.space.quiz_api

interface GetQuestionsCount {
    suspend operator fun invoke(subjectTitle: String): Int
}