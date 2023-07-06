package com.space.main_impl.data.local.database.dao

import androidx.room.*
import com.space.main_impl.data.local.database.model.user.QuizUserSubjectEntity

@Dao
interface QuizUserSubjectsDao {

    @Delete
    suspend fun deleteUserSubject(userSubject: QuizUserSubjectEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserSubject(userSubject: QuizUserSubjectEntity)

    @Query("SELECT * FROM user_subject WHERE username=:username")
    suspend fun getUserSubjects(username: String): List<QuizUserSubjectEntity>

    @Query("SELECT * FROM user_subject WHERE username=:username AND quizTitle=:quizTitle")
    suspend fun getUserSubjectByTitleIfExists(
        username: String,
        quizTitle: String
    ): QuizUserSubjectEntity?

    @Update
    suspend fun updateUserSubject(userSubject: QuizUserSubjectEntity)

}
