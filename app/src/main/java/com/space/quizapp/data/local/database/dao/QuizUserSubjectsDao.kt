package com.space.quizapp.data.local.database.dao

import androidx.room.*
import com.space.quizapp.data.local.database.model.user.QuizUserSubjectEntity

@Dao
interface QuizUserSubjectsDao {

    @Delete
    suspend fun deleteUserSubject(userSubject: QuizUserSubjectEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserSubject(userSubject: QuizUserSubjectEntity)

    @Query("SELECT * FROM user_subject WHERE username=:username")
    suspend fun getUserSubjects(username: String): List<QuizUserSubjectEntity>

    @Query("SELECT * FROM user_subject WHERE username=:username AND subjectId=:subjectId")
    suspend fun getUserSubjectByIdIfExists(username: String, subjectId: Int): QuizUserSubjectEntity?
}
