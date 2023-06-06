package com.space.quizapp.data.local.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.space.quizapp.data.local.database.model.user.QuizUserSubjectEntity

interface QuizUserSubjectsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserSubject(userSubject: QuizUserSubjectEntity)

    @Query("SELECT * FROM user_subject WHERE username=:username")
    suspend fun retrieveUserSubjects(username: String): QuizUserSubjectEntity

    @Query("SELECT * FROM user_subject WHERE username=:username AND subjectId=:subjectId")
    suspend fun retrieveUserSubjectWithId(username: String, subjectId: Int): QuizUserSubjectEntity
}
