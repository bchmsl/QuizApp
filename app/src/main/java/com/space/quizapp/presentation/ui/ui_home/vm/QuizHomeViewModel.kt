package com.space.quizapp.presentation.ui.ui_home.vm

import com.space.quizapp.common.extensions.coroutines.executeAsync
import com.space.quizapp.common.util.QuizCustomThrowable
import com.space.quizapp.common.util.S
import com.space.quizapp.data.local.datastore.QuizUserDataStoreManager.Companion.EMPTY_STRING
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel
import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.model.quiz.QuizSubjectUiModel
import com.space.quizapp.presentation.model.quiz.mapper.QuizSubjectUiMapper
import com.space.quizapp.presentation.model.user.QuizUserUiModel
import com.space.quizapp.presentation.model.user.mapper.user.QuizUserUiMapper
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuizHomeViewModel(
    private val readUserDataUC: QuizBaseUseCase<Unit, QuizUserDomainModel>,
    private val saveUserTokenUC: QuizBaseUseCase<String, Unit>,
    private val retrieveSubjectsUC: QuizBaseUseCase<Unit, List<QuizSubjectDomainModel>>,
    private val userMapper: QuizUserUiMapper,
    private val subjectMapper: QuizSubjectUiMapper,
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
                val userDomainModel = readUserDataUC()
                _userState.emit(userMapper.toUi(userDomainModel))
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
            _subjectsState.emit(data.map { subjectMapper.toUi(it) })
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
