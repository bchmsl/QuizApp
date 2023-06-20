package com.space.quizapp.data.local.database.dao

import androidx.room.*
import com.space.quizapp.data.local.database.model.quiz.QuizQuestionEntity

@Dao
interface QuizQuestionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestionsList(questions: List<QuizQuestionEntity>)

    @Query("SELECT * FROM questions WHERE subjectTitle=:subjectTitle AND isAnswered=false")
    suspend fun getNextQuestionBySubjectTitle(subjectTitle: String): QuizQuestionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: QuizQuestionEntity)

    @Update
    suspend fun updateQuestion(question: QuizQuestionEntity)

    @Query("SELECT * FROM questions WHERE subjectTitle=:subjectTitle")
    suspend fun getAllQuestions(subjectTitle: String): List<QuizQuestionEntity>
}
