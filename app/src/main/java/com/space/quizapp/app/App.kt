package com.space.quizapp.app

import android.app.Application
import com.space.quizapp.di.module.dbModule
import com.space.quizapp.di.module.repositoryModule
import com.space.quizapp.di.module.useCaseModule
import com.space.quizapp.di.module.viewModelModule
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
                useCaseModule
            )
        }
    }
}
