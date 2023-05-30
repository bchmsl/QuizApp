package com.space.quizapp.presentation.ui.home.vm

import com.space.quizapp.common.extensions.executeAsync
import com.space.quizapp.data.local.datastore.QuizUserDataStoreManager.Companion.EMPTY_STRING
import com.space.quizapp.domain.usecase.user.read_user_data.QuizRetrieveUserDataUseCase
import com.space.quizapp.domain.usecase.user.save_user_token.QuizSaveUserTokenUseCase
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.common.model.QuizUserUiModel
import com.space.quizapp.presentation.common.model.mapper.QuizUserDomainUiMapper
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuizHomeViewModel(
    private val retrieveUserDataUC: QuizRetrieveUserDataUseCase,
    private val saveUserTokenUC: QuizSaveUserTokenUseCase,
    private val userDomainUiMapper: QuizUserDomainUiMapper
) : QuizBaseViewModel() {

    private val _userState = MutableStateFlow(QuizUserUiModel(""))
    val userState get() = _userState.asStateFlow()

    fun retrieveUserInfo() {
        executeAsync(IO) {
            try {
                retrieveUserDataUC().collect { userDomainModel ->
                    _userState.emit(userDomainUiMapper(userDomainModel))
                }
            } catch (e: Exception) {
                setError(e)
            }
        }
    }

    fun logOut() {
        executeAsync(IO) {
            saveUserTokenUC(EMPTY_STRING)
            navigate(QuizFragmentDirections.START)
        }
    }

}
