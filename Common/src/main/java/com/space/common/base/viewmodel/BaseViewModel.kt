package com.space.common.base.viewmodel

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val errorState by com.space.common.util.LiveDataDelegate<com.space.common.util.CustomThrowable?>(
        null
    )

    fun postError(throwable: com.space.common.util.CustomThrowable? = null) {
        errorState.post(throwable)
    }


}
