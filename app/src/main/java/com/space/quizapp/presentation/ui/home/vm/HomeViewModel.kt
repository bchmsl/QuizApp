package com.space.quizapp.presentation.ui.home.vm

import com.space.quizapp.common.extensions.executeAsync
import com.space.quizapp.domain.usecase.user.read_user_data.RetrieveUserDataUseCase
import com.space.quizapp.presentation.base.viewmodel.BaseViewModel
import com.space.quizapp.presentation.common.model.UserUiModel
import com.space.quizapp.presentation.common.model.mapper.UserDomainUiMapper
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val retrieveUserDataUseCase: RetrieveUserDataUseCase,
    private val userDomainUiMapper: UserDomainUiMapper
) : BaseViewModel() {

    private val _userState = MutableStateFlow(UserUiModel(""))
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
