package com.space.quizapp.data.di

import com.space.quizapp.data.local.datastore.QuizUserDataStoreManager
import com.space.quizapp.data.local.datastore.QuizUserDataStoreManagerImpl
import org.koin.dsl.module

val dataStoreModule = module {
    single<QuizUserDataStoreManager> { QuizUserDataStoreManagerImpl(context = get()) }
}
