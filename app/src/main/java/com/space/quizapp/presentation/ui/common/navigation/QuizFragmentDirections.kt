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
        this.directions.arguments.putString(TAG_STRING, argument)
    }

    fun addArgument(argument: Parcelable) {
        this.directions.arguments.putParcelable(TAG_PARCELABLE, argument)
    }

    fun addArgument(argument: Int) {
        this.directions.arguments.putInt(TAG_INT, argument)
    }

    companion object {
        const val TAG_STRING = "extra_tag_string"
        const val TAG_INT = "extra_tag_int"
        const val TAG_PARCELABLE = "extra_tag_parcelable"
    }
}
