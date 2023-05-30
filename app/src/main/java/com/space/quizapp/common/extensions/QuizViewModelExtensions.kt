package com.space.quizapp.common.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun ViewModel.executeAsync(
    coroutineContext: CoroutineContext = Dispatchers.Unconfined,
    block: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch(coroutineContext) {
        block()
    }
}

fun ViewModel.collectAsync(flow: Flow<Any?>, block: CoroutineScope.(Any?) -> Unit) {
    viewModelScope.launch {
        flow.collect {
            block(it)
        }
    }
}
