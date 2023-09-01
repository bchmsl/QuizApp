package com.space.main.data.local.database.dao

import androidx.room.*
import com.space.main.data.local.database.model.user.UserSubjectEntity

@Dao
interface UserSubjectsDao {

    @Delete
    suspend fun deleteUserSubject(userSubject: UserSubjectEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserSubject(userSubject: UserSubjectEntity)

    @Query("SELECT * FROM user_subject WHERE username=:username")
    suspend fun getUserSubjects(username: String): List<UserSubjectEntity>

    @Query("SELECT * FROM user_subject WHERE username=:username AND quizTitle=:quizTitle")
    suspend fun getUserSubjectByTitleIfExists(
        username: String,
        quizTitle: String
    ): UserSubjectEntity?

    @Update
    suspend fun updateUserSubject(userSubject: UserSubjectEntity)

}
