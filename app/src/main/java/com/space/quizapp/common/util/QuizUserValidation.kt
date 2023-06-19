package com.space.quizapp.common.util

import androidx.annotation.StringRes

enum class QuizUserValidation(@StringRes val message: Int) {
    VALID(S.message_valid),
    INVALID_LENGTH_LONG(S.message_error_length_long),
    INVALID_LENGTH_SHORT(S.message_error_length_short),
    INVALID_CONTAINING(S.message_error_containing_symbol);

    companion object {
        fun validateUser(username: String): QuizUserValidation {
            return when {
                username.length < MINIMUM_LENGTH -> INVALID_LENGTH_SHORT
                username.length > MAXIMUM_LENGTH -> INVALID_LENGTH_LONG
                !username.contains(Regex(QuizRegex.USERNAME_PATTERN)) -> INVALID_CONTAINING
                else -> VALID
            }
        }

        private const val MINIMUM_LENGTH = 2
        private const val MAXIMUM_LENGTH = 20
    }
}
