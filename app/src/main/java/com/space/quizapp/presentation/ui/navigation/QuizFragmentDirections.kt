package com.space.quizapp.presentation.ui.navigation

import androidx.navigation.NavDirections
import com.space.quizapp.presentation.ui.start.fragment.QuizStartFragmentDirections

enum class QuizFragmentDirections(val directions: NavDirections) {
    START_TO_HOME(QuizStartFragmentDirections.actionStartFragmentToHomeFragment())
}
