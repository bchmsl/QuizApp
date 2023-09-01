package com.space.quiz_impl.navigation

import android.os.Bundle
import com.space.navigation_api.AppNavigator
import com.space.quiz_api.QuizNavigator

class NavigatorImpl(private val appNavigator: AppNavigator) : QuizNavigator {
    override fun navigateToQuiz(bundle: Bundle) {
        appNavigator.navigateToQuiz(bundle)
    }
}