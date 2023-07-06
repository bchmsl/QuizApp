package com.space.common.base.viewmodel

import androidx.lifecycle.ViewModel

abstract class QuizBaseViewModel : ViewModel() {

    val errorState by com.space.common.util.QuizLiveDataDelegate<com.space.common.util.QuizCustomThrowable?>(
        null
    )

    fun postError(throwable: com.space.common.util.QuizCustomThrowable? = null) {
        errorState.post(throwable)
    }


}
