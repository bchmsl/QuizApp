package com.space.quizapp.presentation.ui.common.view.dialog

import android.content.Context
import android.view.LayoutInflater
import com.space.quizapp.common.util.S
import com.space.quizapp.databinding.QuizLayoutDialogPromptBinding

class QuizDialogPromptView(context: Context) : QuizDialogView(context) {
    override val binding =
        QuizLayoutDialogPromptBinding.inflate(LayoutInflater.from(context), this, true)

    fun setContent(
        message: String,
        positiveButtonText: String = context.getString(S.yes),
        negativeButtonText: String = context.getString(S.no)
    ): QuizDialogPromptView {
        with(binding) {
            promptTextView.text = message
            positiveButton.text = positiveButtonText
            negativeButton.text = negativeButtonText
        }
        alertDialog.setView(this)
        return this
    }

    fun onPositiveButtonListener(block: () -> Unit): QuizDialogPromptView {
        binding.positiveButton.setOnClickListener {
            block.invoke()
        }
        dismiss()
        return this
    }

    fun onNegativeButtonListener(block: () -> Unit): QuizDialogPromptView {
        binding.negativeButton.setOnClickListener {
            block.invoke()

        }
        dismiss()
        return this
    }
}
