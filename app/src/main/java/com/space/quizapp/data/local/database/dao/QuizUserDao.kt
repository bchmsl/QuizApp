package com.space.quizapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.space.quizapp.data.local.database.model.QuizUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizUserDao {

    @Insert
    fun saveUser(userEntity: QuizUserEntity)

    @Query("SELECT * FROM users WHERE token=:token")
    fun retrieveUserInfo(token: String): Flow<List<QuizUserEntity>>

}
