package com.space.main.data.local.database.dao

import androidx.room.*
import com.space.common.model.question.data.QuestionEntity

@Dao
interface QuestionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestionsList(questions: List<QuestionEntity>)

    @Query("SELECT * FROM questions WHERE subjectTitle=:subjectTitle AND isAnswered=false")
    suspend fun getNextQuestionBySubjectTitle(subjectTitle: String): QuestionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: QuestionEntity)

    @Update
    suspend fun updateQuestion(question: QuestionEntity)

    @Query("SELECT * FROM questions WHERE subjectTitle=:subjectTitle")
    suspend fun getAllQuestions(subjectTitle: String): List<QuestionEntity>

    @Query("SELECT COUNT(*) FROM questions WHERE subjectTitle=:subjectTitle")
    suspend fun countQuestions(subjectTitle: String): Int
}
