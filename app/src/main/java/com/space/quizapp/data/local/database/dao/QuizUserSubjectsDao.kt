package com.space.quizapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.space.quizapp.data.local.database.model.user.QuizUserSubjectEntity

@Dao
interface QuizUserSubjectsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserSubject(userSubject: QuizUserSubjectEntity)

    @Query("SELECT * FROM user_subject WHERE username=:username")
    suspend fun getUserSubjects(username: String): List<QuizUserSubjectEntity>

    @Query("SELECT * FROM user_subject WHERE username=:username AND subjectId=:subjectId")
    suspend fun getUserSubjectById(username: String, subjectId: Int): QuizUserSubjectEntity
}
