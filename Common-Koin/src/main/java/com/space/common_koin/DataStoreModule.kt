package com.space.common_koin

import com.space.main.data.local.datastore.UserDataStoreManager
import org.koin.dsl.module

val dataStoreModule = module {
    single { UserDataStoreManager(context = get()) }
}
