package com.space.quizapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.space.quizapp.data.local.database.model.user.user.QuizUserEntity

@Dao
interface QuizUserDao {

    @Insert
    suspend fun saveUser(user: QuizUserEntity)

    @Query("SELECT * FROM users WHERE token=:token")
    fun retrieveUserInfo(token: String): QuizUserEntity

    @Query("SELECT token FROM users WHERE username=:username")
    suspend fun checkUser(username: String): String?
}
