package com.space.main_impl.data.local.database.dao

import androidx.room.*
import com.space.common.model.subject.data.QuizSubjectEntity

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
