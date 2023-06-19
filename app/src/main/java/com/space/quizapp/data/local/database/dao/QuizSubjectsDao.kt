package com.space.quizapp.data.local.database.dao

import androidx.room.*
import com.space.quizapp.data.local.database.model.quiz.QuizSubjectEntity

@Dao
interface QuizSubjectsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjects(subjects: List<QuizSubjectEntity>)


    @Query("SELECT * FROM subjects WHERE quizTitle=:quizTitle")
    suspend fun getSubjectByTitle(quizTitle: String): QuizSubjectEntity

    @Query("SELECT * FROM subjects")
    suspend fun getSubjects(): List<QuizSubjectEntity>

//    @Query("SELECT * FROM subjects WHERE subjectId=:subjectId")
//    fun getSubjectById(subjectId: Int): QuizSubjectEntity


}
