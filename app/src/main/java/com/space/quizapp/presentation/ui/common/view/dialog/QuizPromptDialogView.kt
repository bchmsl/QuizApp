package com.space.quizapp.presentation.ui.common.view.dialog

import android.content.Context
import android.view.LayoutInflater
import com.space.quizapp.databinding.QuizLayoutDialogPromptBinding

class QuizPromptDialogView constructor(context: Context) : QuizDialogView(context) {
    override val binding = QuizLayoutDialogPromptBinding
        .inflate(LayoutInflater.from(context), this, false)

    init {

    }

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
            this.negativeButtonText = positiveButtonText
            this.negativeButtonListener = negativeButtonListener
        }

        override fun build(): QuizDialogView {
            return QuizPromptDialogView(context)
        }
    }

    override fun show() {
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
        alertDialog.setView(binding.root)
        alertDialog.setContentView(binding.root)
        super.show()
    }
}
