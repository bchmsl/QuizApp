package com.space.quizapp.common.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.makeSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}
