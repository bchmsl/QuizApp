package com.space.quizapp.common.extensions.utils

import android.util.Log.wtf
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.space.quizapp.common.util.C
import com.space.quizapp.presentation.base.fragment.QuizBaseFragment
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel

fun ImageView.setImage(url: String) {
    Glide.with(this).load(url).into(this)
}

fun <VB : ViewBinding, VM : QuizBaseViewModel> QuizBaseFragment<VB, VM>.withBinding(block: VB.() -> Unit) {
    with(binding) {
        this.block()
    }
}

fun View.makeSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}

fun Any.log(message: Any?, tag: String = "") {
    wtf("TAG $tag ", "\n${message.toString()}\n\n")
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

fun Float.roundToSingleDecimal(): Float {
    return String.format("%.1f", this).toFloat()
}

fun Button.enable(isEnabled: Boolean) {
    setEnabled(isEnabled)
    backgroundTintList = ContextCompat.getColorStateList(
        context,
        if (isEnabled) C.yellow_primary else C.neutral_02_grey
    )
}