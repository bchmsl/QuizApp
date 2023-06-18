package com.space.quizapp.presentation.ui.common.view.dialog

import android.content.Context
import android.view.LayoutInflater
import com.space.quizapp.databinding.QuizLayoutDialogAlertBinding

class QuizDialogAlertView(context: Context) : QuizDialogView(context) {
    override val binding =
        QuizLayoutDialogAlertBinding.inflate(LayoutInflater.from(context), this, true)

    fun setContent(
        message: String
    ): QuizDialogAlertView {
        binding.messageTextView.text = message
        alertDialog.setView(this)
        return this
    }

    fun onButtonClick(block: () -> Unit): QuizDialogAlertView {
        binding.dismissTextView.setOnClickListener {
            block.invoke()
        }
        return this
    }
}