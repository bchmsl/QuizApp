package com.space.common.dialog

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding

abstract class DialogView(context: Context) :
    FrameLayout(context) {
    abstract val binding: ViewBinding
    protected val alertDialog: AlertDialog by lazy { AlertDialog.Builder(context).create() }

    abstract class Builder {
        abstract fun build(): DialogView
    }

    init {
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.setCancelable(false)
    }

    fun dismiss() {
        alertDialog.dismiss()
    }

    open fun show() {
        with(alertDialog) {
            removeAllViews()
            setView(binding.root)
            show()
        }
    }
}

