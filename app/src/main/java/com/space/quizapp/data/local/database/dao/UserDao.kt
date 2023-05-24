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

    @Query("SELECT * FROM users WHERE username=:username")
    fun retrieveUserInfo(username: String): Flow<List<UserEntity>>

}
