package com.space.quizapp.presentation.base.viewmodel

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import com.space.quizapp.common.extensions.coroutines.executeAsync
import com.space.quizapp.common.util.QuizCustomThrowable
import com.space.quizapp.common.util.QuizLiveDataDelegate
import com.space.quizapp.common.util.postValue
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class QuizBaseViewModel : ViewModel() {

    val errorState by QuizLiveDataDelegate<QuizCustomThrowable?>(null)

    fun postError(throwable: QuizCustomThrowable? = null) {
        postValue(errorState) { throwable }
    }

    private val _navigationState = MutableStateFlow<QuizFragmentDirections?>(null)
    val navigationState get() = _navigationState.asStateFlow()

    fun navigate(directions: QuizFragmentDirections, vararg arguments: Any) {
        executeAsync {
            arguments.forEach { argument ->
                when (argument) {
                    is String -> directions.addArgument(argument)
                    is Parcelable -> directions.addArgument(argument)
                    is Int -> directions.addArgument(argument)
                }
            }
            _navigationState.emit(directions)
        }
    }
}
