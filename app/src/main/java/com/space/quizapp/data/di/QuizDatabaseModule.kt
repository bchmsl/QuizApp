package com.space.quizapp.data.di

import android.content.Context
import androidx.room.Room
import com.space.quizapp.data.local.database.dao.QuizUserDao
import com.space.quizapp.data.local.database.database.QuizUsersDatabase
import org.koin.dsl.module

private fun provideUsersDatabase(context: Context): QuizUsersDatabase =
    Room.databaseBuilder(context, QuizUsersDatabase::class.java, QuizUsersDatabase.DATABASE_NAME)
        .build()

private fun provideUserDao(database: QuizUsersDatabase): QuizUserDao =
    database.userDao()

val dbModule = module {
    single { provideUserDao(get()) }
    single { provideUsersDatabase(get()) }
}
