package com.space.common.dialog

import android.content.Context

object DialogFactory {
    fun createDialog(dialogType: Dialog, context: Context): DialogView.Builder {
        return when (dialogType) {
            Dialog.DIALOG_PROMPT -> PromptDialogView.Builder(context)
            Dialog.DIALOG_ALERT -> AlertDialogView.Builder(context)
        }
    }

    enum class Dialog {
        DIALOG_ALERT,
        DIALOG_PROMPT
    }
}
