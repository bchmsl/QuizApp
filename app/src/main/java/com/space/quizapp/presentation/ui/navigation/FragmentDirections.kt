package com.space.quizapp.presentation.ui.navigation

import androidx.navigation.NavDirections
import com.space.quizapp.presentation.ui.start.fragment.StartFragmentDirections

enum class FragmentDirections(val directions: NavDirections) {
    START_TO_HOME(StartFragmentDirections.actionStartFragmentToHomeFragment())
}
