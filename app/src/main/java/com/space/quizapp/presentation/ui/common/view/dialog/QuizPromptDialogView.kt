package com.space.quizapp.presentation.ui.common.view.dialog

import android.content.Context
import android.view.LayoutInflater
import com.space.quizapp.databinding.QuizLayoutDialogPromptBinding

class QuizPromptDialogView constructor(context: Context) : QuizDialogView(context) {
    override val binding = QuizLayoutDialogPromptBinding
        .inflate(LayoutInflater.from(context), this, false)

    private var message = ""
    private var positiveButtonText = ""
    private var positiveButtonListener: ((QuizPromptDialogView) -> Unit)? = null
    private var negativeButtonText = ""
    private var negativeButtonListener: ((QuizPromptDialogView) -> Unit)? = null

    class Builder(private val context: Context) : QuizDialogView.Builder() {
        private var message = ""
        private var positiveButtonText = ""
        private var positiveButtonListener: ((QuizPromptDialogView) -> Unit)? = null
        private var negativeButtonText = ""
        private var negativeButtonListener: ((QuizPromptDialogView) -> Unit)? = null

        fun setMessage(message: String) = apply {
            this.message = message
        }

        fun setPositiveButton(
            positiveButtonText: String,
            positiveButtonListener: (QuizPromptDialogView) -> Unit
        ) = apply {
            this.positiveButtonText = positiveButtonText
            this.positiveButtonListener = positiveButtonListener
        }

        fun setNegativeButton(
            negativeButtonText: String,
            negativeButtonListener: (QuizPromptDialogView) -> Unit
        ) = apply {
            this.negativeButtonText = negativeButtonText
            this.negativeButtonListener = negativeButtonListener
        }

        override fun build(): QuizDialogView {
            val dialog = QuizPromptDialogView(context)
            with(dialog) {
                message = this@Builder.message
                positiveButtonText = this@Builder.positiveButtonText
                positiveButtonListener = this@Builder.positiveButtonListener
                negativeButtonText = this@Builder.negativeButtonText
                negativeButtonListener = this@Builder.negativeButtonListener
            }
            return dialog
        }
    }

    override fun show() {
        super.show()
        with(binding) {
            promptTextView.text = message
            with(positiveButton) {
                text = positiveButtonText
                setOnClickListener { positiveButtonListener?.invoke(this@QuizPromptDialogView) }
            }
            with(negativeButton) {
                text = negativeButtonText
                setOnClickListener { negativeButtonListener?.invoke(this@QuizPromptDialogView) }
            }
        }
    }
}