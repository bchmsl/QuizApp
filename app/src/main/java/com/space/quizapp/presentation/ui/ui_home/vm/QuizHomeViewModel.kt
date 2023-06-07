package com.space.quizapp.presentation.ui.ui_home.vm

import com.space.quizapp.common.extensions.coroutines.executeAsync
import com.space.quizapp.common.util.QuizCustomThrowable
import com.space.quizapp.common.util.S
import com.space.quizapp.data.local.datastore.QuizUserDataStoreManager.Companion.EMPTY_STRING
import com.space.quizapp.domain.usecase.quiz.retrieve_subjects.QuizRetrieveSubjectsUseCase
import com.space.quizapp.domain.usecase.user.read_user_data.QuizRetrieveUserDataUseCase
import com.space.quizapp.domain.usecase.user.save_user_token.QuizSaveUserTokenUseCase
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.model.quiz.QuizSubjectUiModel
import com.space.quizapp.presentation.model.quiz.mapper.subject.QuizSubjectDomainUiMapper
import com.space.quizapp.presentation.model.user.QuizUserUiModel
import com.space.quizapp.presentation.model.user.mapper.user.user.QuizUserDomainUiMapper
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuizHomeViewModel(
    private val retrieveUserDataUC: QuizRetrieveUserDataUseCase,
    private val saveUserTokenUC: QuizSaveUserTokenUseCase,
    private val userDomainUiMapper: QuizUserDomainUiMapper,

    private val subjectDomainUiMapper: QuizSubjectDomainUiMapper,
    private val retrieveSubjectsUC: QuizRetrieveSubjectsUseCase
) : QuizBaseViewModel() {

    private val _userState = MutableStateFlow(QuizUserUiModel(""))
    val userState get() = _userState.asStateFlow()

    private val _subjectsState = MutableStateFlow<List<QuizSubjectUiModel>?>(null)
    val subjectsState get() = _subjectsState.asStateFlow()

    private val _loadingState = MutableStateFlow(true)
    val loadingState get() = _loadingState.asStateFlow()

    fun retrieveUserInfo() {
        executeAsync(IO) {
            try {
                val userDomainModel = retrieveUserDataUC()
                _userState.emit(userDomainUiMapper(userDomainModel))
            } catch (e: Throwable) {
                emitError(QuizCustomThrowable(e.message))
            }
        }
    }

    fun retrieveSubjects() {
        executeAsync(IO) {
            _loadingState.emit(true)
            val data = retrieveSubjectsUC()
            if (data.isEmpty()) {
                emitError(QuizCustomThrowable(S.error_bad_request))
            }
            _subjectsState.emit(data.map { subjectDomainUiMapper(it) })
            _loadingState.emit(false)
        }
    }

    fun logOut() {
        executeAsync(IO) {
            saveUserTokenUC(EMPTY_STRING)
            navigate(QuizFragmentDirections.START)
        }
    }
}
