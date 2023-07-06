package com.space.quizapp.data.local.database.dao

import androidx.room.*
import com.space.common.model.question.data.QuizQuestionEntity

@Dao
interface QuizQuestionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestionsList(questions: List<com.space.common.model.question.data.QuizQuestionEntity>)

    @Query("SELECT * FROM questions WHERE subjectTitle=:subjectTitle AND isAnswered=false")
    suspend fun getNextQuestionBySubjectTitle(subjectTitle: String): com.space.common.model.question.data.QuizQuestionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: com.space.common.model.question.data.QuizQuestionEntity)

    @Update
    suspend fun updateQuestion(question: com.space.common.model.question.data.QuizQuestionEntity)

    @Query("SELECT * FROM questions WHERE subjectTitle=:subjectTitle")
    suspend fun getAllQuestions(subjectTitle: String): List<com.space.common.model.question.data.QuizQuestionEntity>

    @Query("SELECT COUNT(*) FROM questions WHERE subjectTitle=:subjectTitle")
    suspend fun countQuestions(subjectTitle: String): Int
}
