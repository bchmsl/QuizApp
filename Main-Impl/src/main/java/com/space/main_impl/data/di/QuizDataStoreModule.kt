package com.space.main_impl.data.di

import com.space.main_impl.data.local.datastore.QuizUserDataStoreManager
import org.koin.dsl.module

val dataStoreModule = module {
    single { QuizUserDataStoreManager(context = get()) }
}
