package com.space.quizapp.presentation.ui.common.navigation

import androidx.navigation.NavDirections
import com.space.quizapp.NavGraphDirections

enum class QuizFragmentDirections(val directions: NavDirections) {
    HOME(NavGraphDirections.actionGlobalHomeFragment()),
    START(NavGraphDirections.actionGlobalStartFragment()),
    QUESTION(NavGraphDirections.actionGlobalQuestionFragment()),
    POINTS(NavGraphDirections.actionGlobalPointsFragment())
}
