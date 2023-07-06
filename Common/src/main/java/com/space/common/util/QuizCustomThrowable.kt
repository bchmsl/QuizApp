package com.space.common.util

import androidx.annotation.StringRes

class QuizCustomThrowable() : Throwable() {

    var errorResource: Int? = null
        private set

    var errorString: String? = null
        private set

    constructor(@StringRes errorMessage: Int) : this() {
        this.errorResource = errorMessage
    }

    constructor(errorMessage: String?) : this() {
        this.errorString = errorMessage.toString()
    }
}
