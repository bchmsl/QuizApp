package com.space.quizapp.presentation.ui.common.view.dialog

import android.content.Context

object QuizDialogFactory {
    fun createDialog(dialogType: Dialog, context: Context): QuizDialogView.Builder {
        return when (dialogType) {
            Dialog.DIALOG_PROMPT -> QuizPromptDialogView.Builder(context)
            Dialog.DIALOG_ALERT -> QuizAlertDialogView.Builder(context)
        }
    }

    enum class Dialog {
        DIALOG_ALERT,
        DIALOG_PROMPT
    }
}
