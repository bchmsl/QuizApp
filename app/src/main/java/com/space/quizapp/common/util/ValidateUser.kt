package com.space.quizapp.common.util

import androidx.annotation.StringRes

enum class ValidateUser(@StringRes val message: Int?) {
    VALID(null),
    INVALID_LENGTH_LONG(S.error_length_long),
    INVALID_LENGTH_SHORT(S.error_length_short),
    INVALID_CONTAINING(S.error_containing_symbol);


    companion object {
        fun validate(s: String): ValidateUser {
            return when {
                s.length <= 3 -> INVALID_LENGTH_SHORT
                s.length > 20 -> INVALID_LENGTH_LONG
                s.contains("") -> INVALID_CONTAINING
                else -> VALID
            }
        }
    }
}

