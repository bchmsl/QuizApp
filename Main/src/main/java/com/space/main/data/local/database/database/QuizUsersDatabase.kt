package com.space.main.data.local.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.space.common.model.question.data.QuizQuestionEntity
import com.space.common.model.subject.data.QuizSubjectEntity
import com.space.main.data.local.database.dao.QuizQuestionsDao
import com.space.main.data.local.database.dao.QuizSubjectsDao
import com.space.main.data.local.database.dao.QuizUserDao
import com.space.main.data.local.database.dao.QuizUserSubjectsDao
import com.space.main.data.local.database.model.user.QuizUserEntity
import com.space.main.data.local.database.model.user.QuizUserSubjectEntity

@Database(
    entities = [QuizUserEntity::class, QuizUserSubjectEntity::class, QuizSubjectEntity::class, QuizQuestionEntity::class],
    version = 1
)
abstract class QuizUsersDatabase : RoomDatabase() {
    abstract fun userDao(): QuizUserDao
    abstract fun subjectsDao(): QuizSubjectsDao
    abstract fun questionsDao(): QuizQuestionsDao
    abstract fun userSubjectsDao(): QuizUserSubjectsDao

    companion object {
        const val DATABASE_NAME = "users_db"
    }
}
