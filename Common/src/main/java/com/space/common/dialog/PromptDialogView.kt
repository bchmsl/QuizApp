package com.space.common.dialog

import android.content.Context
import android.view.LayoutInflater
import com.space.corecommon.databinding.LayoutDialogPromptBinding

class PromptDialogView constructor(context: Context) : DialogView(context) {
    override val binding = LayoutDialogPromptBinding
        .inflate(LayoutInflater.from(context), this, false)

    private var message = ""
    private var positiveButtonText = ""
    private var positiveButtonListener: ((PromptDialogView) -> Unit)? = null
    private var negativeButtonText = ""
    private var negativeButtonListener: ((PromptDialogView) -> Unit)? = null

    class Builder(private val context: Context) : DialogView.Builder() {
        private var message = ""
        private var positiveButtonText = ""
        private var positiveButtonListener: ((PromptDialogView) -> Unit)? = null
        private var negativeButtonText = ""
        private var negativeButtonListener: ((PromptDialogView) -> Unit)? = null

        fun setMessage(message: String) = apply {
            this.message = message
        }

        fun setPositiveButton(
            positiveButtonText: String,
            positiveButtonListener: (PromptDialogView) -> Unit
        ) = apply {
            this.positiveButtonText = positiveButtonText
            this.positiveButtonListener = positiveButtonListener
        }

        fun setNegativeButton(
            negativeButtonText: String,
            negativeButtonListener: (PromptDialogView) -> Unit
        ) = apply {
            this.negativeButtonText = negativeButtonText
            this.negativeButtonListener = negativeButtonListener
        }

        override fun build(): DialogView {
            return PromptDialogView(context).also {
                it.message = message
                it.positiveButtonText = positiveButtonText
                it.positiveButtonListener = positiveButtonListener
                it.negativeButtonText = negativeButtonText
                it.negativeButtonListener = negativeButtonListener
            }
        }
    }

    override fun show() {
        super.show()
        with(binding) {
            promptTextView.text = message
            with(positiveButton) {
                text = positiveButtonText
                setOnClickListener { positiveButtonListener?.invoke(this@PromptDialogView) }
            }
            with(negativeButton) {
                text = negativeButtonText
                setOnClickListener { negativeButtonListener?.invoke(this@PromptDialogView) }
            }
        }
    }
}
