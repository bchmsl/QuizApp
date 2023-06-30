package com.space.quizapp.presentation.ui.common.view.dialog

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding

abstract class QuizDialogView(context: Context) :
    FrameLayout(context) {
    abstract val binding: ViewBinding
    protected val alertDialog: AlertDialog by lazy { AlertDialog.Builder(context).create() }

    abstract class Builder {
        abstract fun build(): QuizDialogView
    }

    init {
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.setCancelable(false)
    }

    open fun show() {
        alertDialog.show()
    }

    fun dismiss() {
        alertDialog.dismiss()
    }
}

