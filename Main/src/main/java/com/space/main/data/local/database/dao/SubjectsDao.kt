package com.space.main.data.local.database.dao

import androidx.room.*
import com.space.common.model.subject.data.SubjectEntity

@Dao
interface SubjectsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjects(subjects: List<SubjectEntity>)


    @Query("SELECT * FROM subjects WHERE quizTitle=:quizTitle")
    suspend fun getSubjectByTitle(quizTitle: String): SubjectEntity

    @Query("SELECT * FROM subjects")
    suspend fun getSubjects(): List<SubjectEntity>

//    @Query("SELECT * FROM subjects WHERE subjectId=:subjectId")
//    fun getSubjectById(subjectId: Int): QuizSubjectEntity


}
