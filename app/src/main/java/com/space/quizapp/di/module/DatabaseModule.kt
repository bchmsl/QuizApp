package com.space.quizapp.di.module

import android.content.Context
import androidx.room.Room
import com.space.quizapp.data.local.database.dao.UserDao
import com.space.quizapp.data.local.database.database.UsersDatabase
import org.koin.dsl.module

private fun provideUsersDatabase(context: Context): UsersDatabase =
    Room.databaseBuilder(context, UsersDatabase::class.java, UsersDatabase.DATABASE_NAME).build()

private fun provideUserDao(database: UsersDatabase): UserDao =
    database.userDao()

val dbModule = module {
    single { provideUserDao(get()) }
    single { provideUsersDatabase(get()) }
}
