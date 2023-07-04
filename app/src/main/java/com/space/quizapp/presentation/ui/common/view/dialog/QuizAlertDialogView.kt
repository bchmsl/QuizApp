package com.space.quizapp.presentation.ui.common.view.dialog

import android.content.Context
import android.view.LayoutInflater
import com.space.quizapp.databinding.QuizLayoutDialogAlertBinding

class QuizAlertDialogView constructor(context: Context) : QuizDialogView(context) {
    override val binding = QuizLayoutDialogAlertBinding
        .inflate(LayoutInflater.from(context), this, true)

    private var title = ""
    private var message = ""
    private var description = ""
    private var buttonText = ""
    private var buttonListener: ((QuizAlertDialogView) -> Unit)? = null

    init {
    }

    class Builder(private val context: Context) : QuizDialogView.Builder() {
        private var title = ""
        private var message = ""
        private var description = ""
        private var buttonText = ""
        private var buttonListener: ((QuizAlertDialogView) -> Unit)? = null

        fun setTitle(title: String) = apply {
            this.title = title
        }

        fun setMessage(message: String) = apply {
            this.message = message
        }

        fun setDescription(description: String) = apply {
            this.description = description
        }

        fun setButton(
            buttonText: String,
            buttonListener: (QuizAlertDialogView) -> Unit
        ) = apply {
            this.buttonText = buttonText
            this.buttonListener = buttonListener
        }

        override fun build(): QuizDialogView {
            val dialog = QuizAlertDialogView(context)
            with(dialog) {
                title = this@Builder.title
                message = this@Builder.message
                description = this@Builder.description
                buttonText = this@Builder.buttonText
                buttonListener = this@Builder.buttonListener
            }
            return dialog
        }
    }

    override fun show() {
        super.show()
        with(binding) {
            titleTextView.text = title
            messageTextView.text = message
            descriptionTextView.text = description
            with(dismissTextView) {
                text = buttonText
                setOnClickListener { buttonListener?.invoke(this@QuizAlertDialogView) }
            }
        }
    }
}