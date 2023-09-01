package com.space.quiz_api

import android.os.Bundle

interface QuizNavigator {
    companion object {
        const val TAG = "subjectTitle"
    }

    fun navigateToQuiz(bundle: Bundle)
}