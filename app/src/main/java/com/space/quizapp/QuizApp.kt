package com.space.quizapp

import android.app.Application
import com.space.common_koin.dataStoreModule
import com.space.common_koin.dbModule
import com.space.common_koin.retrofitModule
import com.space.common_koin.viewModelModule
import com.space.main.data.di.dataMapperModule
import com.space.main.data.di.repositoryModule
import com.space.main.domain.di.useCaseModule
import com.space.main.presentation.di.uiMapperModule
import com.space.navigation_api.AppNavigator
import com.space.quiz_api.QuizNavigator
import com.space.quiz_impl.navigation.QuizNavigatorImpl
import com.space.quizapp.navigation.AppNavigatorImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class QuizApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@QuizApp)
            modules(
                navModule,
                dbModule,
                repositoryModule,
                useCaseModule,
                dataStoreModule,
                retrofitModule,
                dataMapperModule,
                uiMapperModule,
                navModule,
                viewModelModule,
                com.space.quiz_impl.domain.useCaseModule
            )
        }
    }
}

val navModule = module {
    single<AppNavigator> { AppNavigatorImpl() }
    single<QuizNavigator> { QuizNavigatorImpl(get()) }
}
