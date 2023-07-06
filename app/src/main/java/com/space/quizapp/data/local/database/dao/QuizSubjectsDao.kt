package com.space.quizapp.data.local.database.dao

import androidx.room.*
import com.space.common.model.question.data.QuizSubjectEntity

@Dao
interface QuizSubjectsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjects(subjects: List<com.space.common.model.question.data.QuizSubjectEntity>)


    @Query("SELECT * FROM subjects WHERE quizTitle=:quizTitle")
    suspend fun getSubjectByTitle(quizTitle: String): com.space.common.model.question.data.QuizSubjectEntity

    @Query("SELECT * FROM subjects")
    suspend fun getSubjects(): List<com.space.common.model.question.data.QuizSubjectEntity>

//    @Query("SELECT * FROM subjects WHERE subjectId=:subjectId")
//    fun getSubjectById(subjectId: Int): QuizSubjectEntity


}
