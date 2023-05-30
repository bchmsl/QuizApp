package com.space.quizapp.app

import android.app.Application
import com.space.quizapp.di.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class QuizApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@QuizApp)
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
