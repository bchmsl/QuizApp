package com.space.main.data.local.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.space.common.model.question.data.QuestionEntity
import com.space.common.model.subject.data.SubjectEntity
import com.space.main.data.local.database.dao.QuestionsDao
import com.space.main.data.local.database.dao.SubjectsDao
import com.space.main.data.local.database.dao.UserDao
import com.space.main.data.local.database.dao.UserSubjectsDao
import com.space.main.data.local.database.model.user.UserEntity
import com.space.main.data.local.database.model.user.UserSubjectEntity

@Database(
    entities = [UserEntity::class, UserSubjectEntity::class, SubjectEntity::class, QuestionEntity::class],
    version = 1
)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun subjectsDao(): SubjectsDao
    abstract fun questionsDao(): QuestionsDao
    abstract fun userSubjectsDao(): UserSubjectsDao

    companion object {
        const val DATABASE_NAME = "quiz_db"
    }
}
