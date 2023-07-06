package com.space.quiz_api

interface UserApi {
    fun updateUserGpa()
    fun saveUserPoints(subjectTitle: String, points: Int)
}