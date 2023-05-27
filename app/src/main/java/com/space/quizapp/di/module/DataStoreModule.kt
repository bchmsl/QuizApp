package com.space.quizapp.di.module

import com.space.quizapp.data.local.datastore.UserDataStoreManager
import com.space.quizapp.data.local.datastore.UserDataStoreManagerImpl
import org.koin.dsl.module

val dataStoreModule = module {
    single<UserDataStoreManager> { UserDataStoreManagerImpl(get()) }
}
