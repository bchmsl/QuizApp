package com.space.quizapp.data.local.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.space.quizapp.data.local.database.dao.QuizSubjectDao
import com.space.quizapp.data.local.database.dao.QuizUserDao
import com.space.quizapp.data.local.database.model.QuizSubjectEntity
import com.space.quizapp.data.local.database.model.QuizUserEntity

@Database(entities = [QuizUserEntity::class, QuizSubjectEntity::class], version = 1)
abstract class QuizUsersDatabase : RoomDatabase() {
    abstract fun userDao(): QuizUserDao
    abstract fun subjectDao(): QuizSubjectDao

    companion object {
        const val DATABASE_NAME = "users_db"
    }
}
