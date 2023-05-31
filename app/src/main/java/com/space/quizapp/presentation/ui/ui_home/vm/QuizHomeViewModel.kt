package com.space.quizapp.presentation.ui.ui_home.vm

import com.space.quizapp.common.extensions.coroutines.executeAsync
import com.space.quizapp.common.resource.QuizResource
import com.space.quizapp.common.util.QuizCustomThrowable
import com.space.quizapp.data.local.datastore.QuizUserDataStoreManager.Companion.EMPTY_STRING
import com.space.quizapp.domain.usecase.questions.questions.QuizQuestionsUseCase
import com.space.quizapp.domain.usecase.user.read_user_data.QuizRetrieveUserDataUseCase
import com.space.quizapp.domain.usecase.user.save_user_token.QuizSaveUserTokenUseCase
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.model.quiz.QuizQuestionsUiModel
import com.space.quizapp.presentation.model.quiz.mapper.QuizQuestionsDomainUiMapper
import com.space.quizapp.presentation.model.user.QuizUserUiModel
import com.space.quizapp.presentation.model.user.mapper.QuizUserDomainUiMapper
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

    private val _loadingState = MutableStateFlow(true)
    val loadingState get() = _loadingState.asStateFlow()

    fun retrieveUserInfo() {
        executeAsync(IO) {
            try {
                retrieveUserDataUC().collect { userDomainModel ->
                    _userState.emit(userDomainUiMapper(userDomainModel))
                }
            } catch (e: Throwable) {
                emitError(QuizCustomThrowable(e.message))
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
                _loadingState.emit(it.isLoading)
                when (it) {
                    is QuizResource.Success -> _questionsState.emit(it.data.map {
                        questionsDomainUiMapper(
                            it
                        )
                    })
                    is QuizResource.Error -> emitError(it.error)
                    else -> {}
                }
            }
        }
    }
}
