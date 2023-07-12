package com.space.navigation_api

import android.os.Bundle

interface AppNavigator {
    fun navigateToStart()
    fun navigateToHome()
    fun navigateToQuiz(bundle: Bundle)
    fun navigateToPoints()
}