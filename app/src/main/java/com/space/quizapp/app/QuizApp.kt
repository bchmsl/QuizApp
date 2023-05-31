package com.space.quizapp.app

import android.app.Application
import com.space.quizapp.data.di.dataStoreModule
import com.space.quizapp.data.di.dbModule
import com.space.quizapp.data.di.repositoryModule
import com.space.quizapp.data.di.retrofitModule
import com.space.quizapp.domain.di.useCaseModule
import com.space.quizapp.presentation.di.viewModelModule
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
                dataStoreModule,
                retrofitModule
            )
        }
    }
}
