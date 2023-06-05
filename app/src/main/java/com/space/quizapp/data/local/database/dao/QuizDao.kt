package com.space.quizapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.space.quizapp.data.local.database.model.quiz.subject.QuizEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {
    @Insert
    suspend fun insertQuiz()

    @Query("SELECT * FROM quiz")
    fun retrieveQuestions(): Flow<List<QuizEntity>>

    @Query("DELETE * FROM quiz")
    suspend fun deleteSubjects()
}
