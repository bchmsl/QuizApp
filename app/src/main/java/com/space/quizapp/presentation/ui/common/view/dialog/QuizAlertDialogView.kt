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
            return QuizAlertDialogView(context).also {
                it.title = title
                it.message = message
                it.description = description
                it.buttonText = buttonText
                it.buttonListener = buttonListener
            }
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
