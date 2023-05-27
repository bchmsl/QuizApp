package com.space.quizapp.app

import android.app.Application
import com.space.quizapp.di.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                dbModule,
                repositoryModule,
                viewModelModule,
                useCaseModule,
                dataStoreModule
            )
        }
    }
}
