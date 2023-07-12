package com.space.quizapp.navigation

import android.os.Bundle
import androidx.navigation.NavController
import com.space.navigation_api.AppNavigator
import com.space.quizapp.R
import org.koin.java.KoinJavaComponent.inject

class AppNavigatorImpl : AppNavigator {
    private val navController by inject<NavController>(NavController::class.java)
    override fun navigateToStart() {
        navController.navigate(R.id.action_global_startFragment)
    }

    override fun navigateToHome() {
        navController.navigate(R.id.action_global_homeFragment)
    }

    override fun navigateToQuiz(bundle: Bundle) {
        navController.navigate(R.id.action_global_questionFragment, bundle)
    }

    override fun navigateToPoints() {
        navController.navigate(R.id.action_global_pointsFragment)
    }
}