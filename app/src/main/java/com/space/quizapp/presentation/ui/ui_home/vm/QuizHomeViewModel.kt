package com.space.quizapp.presentation.ui.ui_home.vm

import com.space.quizapp.common.extensions.coroutines.executeAsync
import com.space.quizapp.common.util.QuizCustomThrowable
import com.space.quizapp.common.util.QuizLiveDataDelegate
import com.space.quizapp.common.util.S
import com.space.quizapp.data.local.datastore.QuizUserDataStoreManager.Companion.EMPTY_STRING
import com.space.quizapp.domain.usecase.quiz.QuizRetrieveSubjectsUseCase
import com.space.quizapp.domain.usecase.user.QuizSaveUserTokenUseCase
import com.space.quizapp.domain.usecase.user.read_user_data.QuizReadUserDataUseCase
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.model.quiz.QuizSubjectUiModel
import com.space.quizapp.presentation.model.quiz.mapper.QuizSubjectUiMapper
import com.space.quizapp.presentation.model.user.QuizUserUiModel
import com.space.quizapp.presentation.model.user.mapper.user.QuizUserUiMapper
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections
import kotlinx.coroutines.Dispatchers.IO

class QuizHomeViewModel(
    private val readUserDataUC: QuizReadUserDataUseCase,
    private val saveUserTokenUC: QuizSaveUserTokenUseCase,
    private val retrieveSubjectsUC: QuizRetrieveSubjectsUseCase,
    private val userMapper: QuizUserUiMapper,
    private val subjectMapper: QuizSubjectUiMapper,
) : QuizBaseViewModel() {

    val userState by QuizLiveDataDelegate<QuizUserUiModel?>(null)
    val subjectsState by QuizLiveDataDelegate<List<QuizSubjectUiModel>?>(null)
    val loadingState by QuizLiveDataDelegate(true)

    fun retrieveUserInfo() {
        executeAsync(IO) {
            try {
                userState.post(userMapper.toUi(readUserDataUC()))
            } catch (e: Throwable) {
                postError(QuizCustomThrowable(e.message))
            }
        }
    }

    fun retrieveSubjects() {
        executeAsync(IO) {
            val data = retrieveSubjectsUC()
            if (data.isEmpty()) {
                postError(QuizCustomThrowable(S.error_bad_request))
            }
            subjectsState.post(subjectMapper.toUiList(data))
            loadingState.post(false)
        }
    }

    fun logOut() {
        executeAsync(IO) {
            saveUserTokenUC(EMPTY_STRING)
            navigate(QuizFragmentDirections.START)
        }
    }
}