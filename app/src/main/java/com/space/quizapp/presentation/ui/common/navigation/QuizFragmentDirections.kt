package com.space.quizapp.presentation.ui.common.navigation

import androidx.navigation.NavDirections
import com.space.quizapp.NavGraphDirections

enum class QuizFragmentDirections(val directions: NavDirections) {
    HOME(NavGraphDirections.actionGlobalHomeFragment()),
    START(NavGraphDirections.actionGlobalStartFragment()),
    QUESTION(NavGraphDirections.actionGlobalQuestionFragment()),
    POINTS(NavGraphDirections.actionGlobalPointsFragment());

    fun addArgument(argument: String) {
        this.directions.arguments.putString(TAG_STRING, argument)
    }

    companion object {
        const val TAG_STRING = "extra_tag_string"
    }
}
