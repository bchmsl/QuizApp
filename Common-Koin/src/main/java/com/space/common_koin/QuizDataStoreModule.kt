package com.space.common_koin

import com.space.main.data.local.datastore.QuizUserDataStoreManager
import org.koin.dsl.module

val dataStoreModule = module {
    single { QuizUserDataStoreManager(context = get()) }
}
