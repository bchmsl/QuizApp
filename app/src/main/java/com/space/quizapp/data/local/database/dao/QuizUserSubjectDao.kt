package com.space.quizapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.space.quizapp.data.local.database.model.user.subject.QuizUserSubjectEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizUserSubjectDao {

    @Insert
    suspend fun saveSubject(subjectEntity: QuizUserSubjectEntity)

    @Query("SELECT * FROM subjects WHERE username=:username")
    fun retrieveSubjectByUserName(username: String): Flow<List<QuizUserSubjectEntity>>
}
