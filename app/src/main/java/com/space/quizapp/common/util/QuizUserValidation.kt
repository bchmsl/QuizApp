package com.space.quizapp.common.util

import androidx.annotation.StringRes

enum class QuizUserValidation(@StringRes val message: Int) {
    VALID(S.message_valid),
    INVALID_LENGTH_LONG(S.message_error_length_long),
    INVALID_LENGTH_SHORT(S.message_error_length_short),
    INVALID_CONTAINING(S.message_error_containing_symbol);

    companion object {
        const val MINIMUM_LENGTH = 2
        const val MAXIMUM_LENGTH = 20
    }
}
