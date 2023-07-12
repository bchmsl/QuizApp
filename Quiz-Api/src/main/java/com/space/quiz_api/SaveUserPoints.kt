package com.space.quiz_api

interface SaveUserPoints {
    suspend operator fun invoke(subjectTitle: String, points: Int)
}