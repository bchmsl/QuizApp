package com.space.quizapp.data.local.database.dao

import androidx.room.*
import com.space.quizapp.data.local.database.model.quiz.questions.QuizQuestionEntity
import com.space.quizapp.data.local.database.model.quiz.subject.QuizSubjectEntity

@Dao
interface QuizSubjectsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjects(subjects: List<QuizSubjectEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: List<QuizQuestionEntity>)

    @Query("SELECT * FROM subjects")
    suspend fun retrieveSubjects(): List<QuizSubjectEntity>

    @Query("SELECT * FROM questions WHERE ")
    suspend fun retrieveQuestionsBySubjectId(subjectId: Int): List<QuizQuestionEntity>

}
