package com.space.main.presentation.ui.ui_home.vm

import androidx.core.os.bundleOf
import com.space.common.base.viewmodel.BaseViewModel
import com.space.common.extensions.coroutines.executeAsync
import com.space.common.model.question.model.SubjectUiMapper
import com.space.common.model.question.model.SubjectUiModel
import com.space.common.util.Constants.EMPTY_STRING
import com.space.common.util.CustomThrowable
import com.space.common.util.LiveDataDelegate
import com.space.main.domain.usecase.quiz.RetrieveSubjectsUseCase
import com.space.main.domain.usecase.user.ReadUserDataUseCase
import com.space.main.domain.usecase.user.SaveUserTokenUseCase
import com.space.main.presentation.model.user.UserUiModel
import com.space.main.presentation.model.user.mapper.user.UserUiMapper
import com.space.navigation_api.AppNavigator
import com.space.quiz_api.QuizNavigator
import kotlinx.coroutines.Dispatchers.IO

class HomeViewModel(
    private val readUserDataUC: ReadUserDataUseCase,
    private val saveUserTokenUC: SaveUserTokenUseCase,
    private val retrieveSubjectsUC: RetrieveSubjectsUseCase,
    private val userMapper: UserUiMapper,
    private val subjectMapper: SubjectUiMapper,
    private val appNavigator: AppNavigator,
    private val quizNavigator: QuizNavigator
) : BaseViewModel() {

    val userState by LiveDataDelegate<UserUiModel?>(null)
    val subjectsState by LiveDataDelegate<List<SubjectUiModel>?>(null)
    val loadingState by LiveDataDelegate(true)

    fun retrieveUserInfo() {
        executeAsync(IO) {
            try {
                userState.post(userMapper.toUi(readUserDataUC()))
            } catch (e: Throwable) {
                postError(CustomThrowable(e.message))
            }
        }
    }

    fun retrieveSubjects() {
        executeAsync(IO) {
            val data = retrieveSubjectsUC()
            if (data.isEmpty()) {
                postError(CustomThrowable(com.space.common.util.S.error_bad_request))
            }
            subjectsState.post(subjectMapper.toUiList(data))
            loadingState.post(false)
        }
    }

    fun logOut() {
        executeAsync(IO) {
            saveUserTokenUC(EMPTY_STRING)
            navigateToStart()
        }
    }

    private fun navigateToStart() {
        appNavigator.navigateToStart()
    }

    fun navigateToQuestion(subjectTitle: String) {
        quizNavigator.navigateToQuiz(bundleOf(QuizNavigator.TAG to subjectTitle))
    }

    fun navigateToPoints() {
        appNavigator.navigateToPoints()
    }
}