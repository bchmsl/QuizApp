package com.space.quizapp.data.local.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.space.quizapp.data.local.database.dao.QuizSubjectsDao
import com.space.quizapp.data.local.database.dao.QuizUserDao
import com.space.quizapp.data.local.database.dao.QuizUserSubjectsDao
import com.space.quizapp.data.local.database.model.quiz.subject.QuizSubjectEntity
import com.space.quizapp.data.local.database.model.user.subjects.QuizUserSubjectEntity
import com.space.quizapp.data.local.database.model.user.user.QuizUserEntity

@Database(
    entities = [QuizUserEntity::class, QuizUserSubjectEntity::class, QuizSubjectEntity::class],
    version = 1
)
abstract class QuizUsersDatabase : RoomDatabase() {
    abstract fun userDao(): QuizUserDao
    abstract fun subjectsDao(): QuizSubjectsDao
    abstract fun userSubjectsDao(): QuizUserSubjectsDao

    companion object {
        const val DATABASE_NAME = "users_db"
    }
}
