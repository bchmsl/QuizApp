package com.space.quizapp.data.local.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.space.quizapp.data.local.database.dao.SubjectDao
import com.space.quizapp.data.local.database.dao.UserDao
import com.space.quizapp.data.local.database.model.SubjectEntity
import com.space.quizapp.data.local.database.model.UserEntity

@Database(entities = [UserEntity::class, SubjectEntity::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun subjectDao(): SubjectDao

    companion object {
        const val DATABASE_NAME = "users_db"
    }
}
