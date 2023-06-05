package com.space.quizapp.data.local.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.space.quizapp.data.local.database.dao.QuizUserDao
import com.space.quizapp.data.local.database.dao.QuizUserSubjectDao
import com.space.quizapp.data.local.database.model.user.subject.QuizUserSubjectEntity
import com.space.quizapp.data.local.database.model.user.user.QuizUserEntity

@Database(entities = [QuizUserEntity::class, QuizUserSubjectEntity::class], version = 1)
abstract class QuizUsersDatabase : RoomDatabase() {
    abstract fun userDao(): QuizUserDao
    abstract fun subjectDao(): QuizUserSubjectDao

    companion object {
        const val DATABASE_NAME = "users_db"
    }
}
