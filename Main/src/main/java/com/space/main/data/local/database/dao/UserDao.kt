package com.space.main.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.space.main.data.local.database.model.user.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE username=:username")
    suspend fun getUser(username: String): UserEntity

    @Query("SELECT token FROM users WHERE username=:username")
    suspend fun getUserTokenOrNull(username: String): String?

    @Query("SELECT * FROM users WHERE token=:userToken")
    suspend fun getUserByToken(userToken: String): UserEntity
}
