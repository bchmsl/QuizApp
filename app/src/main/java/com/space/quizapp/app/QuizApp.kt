package com.space.quizapp.app

import android.app.Application
import com.space.main_impl.data.di.dataMapperModule
import com.space.main_impl.data.di.repositoryModule
import com.space.main_impl.data.di.retrofitModule
import com.space.main_impl.domain.di.useCaseModule
import com.space.main_impl.presentation.di.uiMapperModule
import com.space.main_impl.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class QuizApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@QuizApp)
            modules(
                com.space.main_impl.data.di.dbModule,
                repositoryModule,
                viewModelModule,
                useCaseModule,
                com.space.main_impl.data.di.dataStoreModule,
                retrofitModule,
                dataMapperModule,
                uiMapperModule
            )
        }
    }
}
