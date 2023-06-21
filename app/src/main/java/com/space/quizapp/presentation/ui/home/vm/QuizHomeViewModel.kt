package com.space.quizapp.presentation.ui.home.vm

import com.space.quizapp.common.extensions.executeAsync
import com.space.quizapp.domain.usecase.user.read_user_data.QuizRetrieveUserDataUseCase
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.common.model.QuizUserUiModel
import com.space.quizapp.presentation.common.model.mapper.QuizUserDomainUiMapper
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuizHomeViewModel(
    private val retrieveUserDataUseCase: QuizRetrieveUserDataUseCase,
    private val userDomainUiMapper: QuizUserDomainUiMapper
) : QuizBaseViewModel() {

    private val _userState = MutableStateFlow(QuizUserUiModel(""))
    val userState get() = _userState.asStateFlow()

    fun retrieveUserInfo() {
        executeAsync(IO) {
            try {
                retrieveUserDataUseCase().collect { userDomainModel ->
                    _userState.emit(userDomainUiMapper(userDomainModel))
                }
            } catch (e: Exception) {
                setError(e)
            }
        }
    }

}
