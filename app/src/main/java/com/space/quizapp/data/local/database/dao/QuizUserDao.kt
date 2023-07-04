package com.space.quizapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.space.quizapp.data.local.database.model.user.QuizUserEntity

@Dao
interface QuizUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: QuizUserEntity)

    @Query("SELECT * FROM users WHERE token=:token")
    fun getUser(token: String): QuizUserEntity

    @Query("SELECT token FROM users WHERE username=:username")
    suspend fun getUserTokenOrNull(username: String): String?
}
