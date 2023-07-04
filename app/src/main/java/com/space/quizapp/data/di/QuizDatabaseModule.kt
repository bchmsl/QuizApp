package com.space.quizapp.data.di

import android.content.Context
import androidx.room.Room
import com.space.quizapp.data.local.database.dao.QuizSubjectsDao
import com.space.quizapp.data.local.database.dao.QuizUserDao
import com.space.quizapp.data.local.database.dao.QuizUserSubjectsDao
import com.space.quizapp.data.local.database.database.QuizUsersDatabase
import org.koin.dsl.module

private fun provideUsersDatabase(context: Context): QuizUsersDatabase =
    Room.databaseBuilder(context, QuizUsersDatabase::class.java, QuizUsersDatabase.DATABASE_NAME)
        .build()

private fun provideUserDao(database: QuizUsersDatabase): QuizUserDao =
    database.userDao()

private fun provideUserSubjectsDao(database: QuizUsersDatabase): QuizUserSubjectsDao =
    database.userSubjectsDao()

private fun provideSubjectsDao(database: QuizUsersDatabase): QuizSubjectsDao =
    database.subjectsDao()

val dbModule = module {
    single { provideUserDao(database = get()) }
    single { provideUserSubjectsDao(database = get()) }
    single { provideSubjectsDao(database = get()) }
    single { provideUsersDatabase(context = get()) }
}
