package com.space.quizapp.presentation.ui.common.navigation

import android.os.Parcelable
import androidx.navigation.NavDirections
import com.space.quizapp.NavGraphDirections

enum class QuizFragmentDirections(val directions: NavDirections) {
    HOME(NavGraphDirections.actionGlobalHomeFragment()),
    START(NavGraphDirections.actionGlobalStartFragment()),
    QUESTION(NavGraphDirections.actionGlobalQuestionFragment()),
    POINTS(NavGraphDirections.actionGlobalPointsFragment());

    fun addArgument(argument: String) {
        this.directions.arguments.putString(TAG, argument)
    }

    fun addArgument(argument: Parcelable) {
        this.directions.arguments.putParcelable(TAG, argument)
    }

    companion object {
        const val TAG = "extra_tag"
    }
}
