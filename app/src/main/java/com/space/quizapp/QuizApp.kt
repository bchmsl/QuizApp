package com.space.quizapp

import android.app.Application
import com.space.common_koin.dataStoreModule
import com.space.common_koin.databaseModule
import com.space.common_koin.retrofitModule
import com.space.common_koin.viewModelModule
import com.space.main.data.di.dataMapperModule
import com.space.main.data.di.repositoryModule
import com.space.main.presentation.di.uiMapperModule
import com.space.quizapp.navigation.appNavigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.space.main.domain.di.useCaseModule as mainUseCaseModule
import com.space.quiz_impl.domain.useCaseModule as quizUseCaseModule

class QuizApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@QuizApp)
            modules(
                appNavigationModule,
                databaseModule,
                dataStoreModule,
                retrofitModule,
                viewModelModule,
                dataMapperModule,
                repositoryModule,
                mainUseCaseModule,
                quizUseCaseModule,
                uiMapperModule
            )
        }
    }
}