package com.space.navigation_impl.navigation

import android.os.Bundle
import androidx.navigation.NavController
import com.space.navigation_api.AppNavigator
import com.space.navigation_impl.R

class AppNavigatorImpl(private val navController: NavController) : AppNavigator {
    override fun navigateToStart() {
        navController.navigate(R.id.action_global_startFragment)
    }

    override fun navigateToHome() {
        navController.navigate(R.id.action_global_homeFragment)
    }

    override fun navigateToQuiz(bundle: Bundle) {
        navController.navigate(R.id.action_global_questionFragment)
    }

    override fun navigateToPoints() {
        navController.navigate(R.id.action_global_pointsFragment)
    }
}