package com.space.quizapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.space.quizapp.data.local.database.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    fun saveUser(userEntity: UserEntity)

    @Query("SELECT * FROM users WHERE token=:token")
    fun retrieveUserInfo(token: String): Flow<List<UserEntity>>

}
