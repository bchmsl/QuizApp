package com.space.quizapp.common.util

import androidx.annotation.StringRes

class QuizCustomThrowable() : Throwable() {

    @StringRes
    private var _errorResource: Int? = null
    val errorResource get() = _errorResource

    private var _errorString: String? = null
    val errorString get() = _errorString


    constructor(@StringRes errorMessage: Int) : this() {
        this._errorResource = errorMessage
    }

    constructor(errorMessage: String?) : this() {
        this._errorString = errorMessage.toString()
    }
}
