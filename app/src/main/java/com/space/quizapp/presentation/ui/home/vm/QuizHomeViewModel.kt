package com.space.quizapp.presentation.ui.home.vm

import com.space.quizapp.common.extensions.executeAsync
import com.space.quizapp.common.resource.Resource
import com.space.quizapp.data.local.datastore.QuizUserDataStoreManager.Companion.EMPTY_STRING
import com.space.quizapp.domain.usecase.questions.questions.QuizQuestionsUseCase
import com.space.quizapp.domain.usecase.user.read_user_data.QuizRetrieveUserDataUseCase
import com.space.quizapp.domain.usecase.user.save_user_token.QuizSaveUserTokenUseCase
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.model.QuizQuestionsUiModel
import com.space.quizapp.presentation.model.QuizUserUiModel
import com.space.quizapp.presentation.model.mapper.QuizQuestionsDomainUiMapper
import com.space.quizapp.presentation.model.mapper.QuizUserDomainUiMapper
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuizHomeViewModel(
    private val retrieveUserDataUC: QuizRetrieveUserDataUseCase,
    private val saveUserTokenUC: QuizSaveUserTokenUseCase,
    private val userDomainUiMapper: QuizUserDomainUiMapper,

    private val questionsDomainUiMapper: QuizQuestionsDomainUiMapper,
    private val questionsUC: QuizQuestionsUseCase
) : QuizBaseViewModel() {

    private val _userState = MutableStateFlow(QuizUserUiModel(""))
    val userState get() = _userState.asStateFlow()

    private val _questionsState = MutableStateFlow<List<QuizQuestionsUiModel>?>(null)
    val questionsState get() = _questionsState.asStateFlow()

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

    fun retrieveQuestions() {
        executeAsync(IO) {
            questionsUC().collect {
                when (it) {
                    is Resource.Success -> _questionsState.emit(it.data.map {
                        questionsDomainUiMapper(
                            it
                        )
                    })
                    is Resource.Error -> setError(it.error)
                    else -> {}
                }
            }
        }
    }
}
