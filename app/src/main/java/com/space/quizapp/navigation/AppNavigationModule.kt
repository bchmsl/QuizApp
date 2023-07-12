package com.space.quizapp.navigation

import com.space.navigation_api.AppNavigator
import com.space.quiz_api.QuizNavigator
import com.space.quiz_impl.navigation.NavigatorImpl
import org.koin.dsl.module

val appNavigationModule = module {
    single<AppNavigator> { AppNavigatorImpl() }
    single<QuizNavigator> { NavigatorImpl(get()) }
}