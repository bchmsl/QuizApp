package com.space.main.presentation.ui.ui_home.vm

import androidx.core.os.bundleOf
import com.space.common.base.viewmodel.QuizBaseViewModel
import com.space.common.extensions.coroutines.executeAsync
import com.space.common.model.question.model.QuizSubjectUiMapper
import com.space.common.model.question.model.QuizSubjectUiModel
import com.space.common.util.QuizConstants.EMPTY_STRING
import com.space.common.util.QuizCustomThrowable
import com.space.common.util.QuizLiveDataDelegate
import com.space.main.domain.usecase.quiz.QuizRetrieveSubjectsUseCase
import com.space.main.domain.usecase.user.QuizReadUserDataUseCase
import com.space.main.domain.usecase.user.QuizSaveUserTokenUseCase
import com.space.main.presentation.model.user.QuizUserUiModel
import com.space.main.presentation.model.user.mapper.user.QuizUserUiMapper
import com.space.navigation_api.AppNavigator
import com.space.quiz_api.QuizNavigator
import kotlinx.coroutines.Dispatchers.IO

class QuizHomeViewModel(
    private val readUserDataUC: QuizReadUserDataUseCase,
    private val saveUserTokenUC: QuizSaveUserTokenUseCase,
    private val retrieveSubjectsUC: QuizRetrieveSubjectsUseCase,
    private val userMapper: QuizUserUiMapper,
    private val subjectMapper: QuizSubjectUiMapper,
    private val appNavigator: AppNavigator,
    private val quizNavigator: QuizNavigator
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
                postError(QuizCustomThrowable(com.space.common.util.S.error_bad_request))
            }
            subjectsState.post(subjectMapper.toUiList(data))
            loadingState.post(false)
        }
    }

    fun logOut() {
        executeAsync(IO) {
            saveUserTokenUC(EMPTY_STRING)
            appNavigator.navigateToStart()
        }
    }

    fun navigateToQuestion(subjectTitle: String) {
        quizNavigator.navigateToQuiz(bundleOf(QuizNavigator.TAG to subjectTitle))
    }

    fun navigateToPoints() {
        appNavigator.navigateToPoints()
    }
}