package com.space.common.extensions.utils

import android.content.res.ColorStateList
import android.view.View
import androidx.annotation.ColorRes
import com.google.android.material.snackbar.Snackbar

fun View.makeSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}


fun View.setColorStateList(@ColorRes colorRes: Int) {
    this.backgroundTintList = ColorStateList.valueOf(context.getColor(colorRes))
}