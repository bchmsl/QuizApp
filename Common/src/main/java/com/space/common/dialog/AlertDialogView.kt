package com.space.common.dialog

import android.content.Context
import android.view.LayoutInflater
import com.space.corecommon.databinding.LayoutDialogAlertBinding

class AlertDialogView constructor(context: Context) : DialogView(context) {
    override val binding = LayoutDialogAlertBinding
        .inflate(LayoutInflater.from(context), this, true)

    private var title = ""
    private var message = ""
    private var description = ""
    private var buttonText = ""
    private var buttonListener: ((AlertDialogView) -> Unit)? = null

    class Builder(private val context: Context) : DialogView.Builder() {
        private var title = ""
        private var message = ""
        private var description = ""
        private var buttonText = ""
        private var buttonListener: ((AlertDialogView) -> Unit)? = null

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
            buttonListener: (AlertDialogView) -> Unit
        ) = apply {
            this.buttonText = buttonText
            this.buttonListener = buttonListener
        }

        override fun build(): DialogView {
            return AlertDialogView(context).also {
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
                setOnClickListener { buttonListener?.invoke(this@AlertDialogView) }
            }
        }
    }
}
