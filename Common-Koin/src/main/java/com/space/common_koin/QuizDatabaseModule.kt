package com.space.common_koin

import android.content.Context
import androidx.room.Room
import com.space.main.data.local.database.dao.QuestionsDao
import com.space.main.data.local.database.dao.SubjectsDao
import com.space.main.data.local.database.dao.UserDao
import com.space.main.data.local.database.dao.UserSubjectsDao
import com.space.main.data.local.database.database.QuizDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { provideQuizDatabase(context = get()) }

    single { provideUserDao(database = get()) }
    single { provideUserSubjectsDao(database = get()) }
    single { provideSubjectsDao(database = get()) }
    single { provideQuestionsDao(database = get()) }
}

private fun provideQuizDatabase(context: Context): QuizDatabase =
    Room.databaseBuilder(context, QuizDatabase::class.java, QuizDatabase.DATABASE_NAME)
        .build()


private fun provideUserDao(database: QuizDatabase): UserDao =
    database.userDao()

private fun provideUserSubjectsDao(database: QuizDatabase): UserSubjectsDao =
    database.userSubjectsDao()

private fun provideSubjectsDao(database: QuizDatabase): SubjectsDao =
    database.subjectsDao()

private fun provideQuestionsDao(database: QuizDatabase): QuestionsDao =
    database.questionsDao()
