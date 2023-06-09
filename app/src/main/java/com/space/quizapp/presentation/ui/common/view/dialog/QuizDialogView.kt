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

    init {
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun show(): QuizDialogView {
        alertDialog.show()
        return this
    }

    fun dismiss() {
        alertDialog.dismiss()
    }
}


