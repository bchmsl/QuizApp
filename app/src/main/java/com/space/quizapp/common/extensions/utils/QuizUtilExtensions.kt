package com.space.quizapp.common.extensions.utils

import android.view.View
import android.widget.ImageView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.space.quizapp.presentation.base.fragment.BaseFragment
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel

fun ImageView.setImage(url: String) {
    Glide.with(this).load(url).into(this)
}

fun <VB : ViewBinding, VM : QuizBaseViewModel> BaseFragment<VB, VM>.withBinding(block: VB.() -> Unit) {
    with(binding) {
        this.block()
    }
}

fun View.makeSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}
