package com.space.main.presentation.ui.ui_points.vm

import androidx.core.os.bundleOf
import com.space.common.base.viewmodel.BaseViewModel
import com.space.common.extensions.coroutines.executeAsync
import com.space.common.util.Constants.EMPTY_STRING
import com.space.common.util.LiveDataDelegate
import com.space.main.domain.usecase.user.SaveUserTokenUseCase
import com.space.main.domain.usecase.user.subject.ReadUserSubjectsUseCase
import com.space.main.presentation.model.user.UserSubjectUiModel
import com.space.main.presentation.model.user.mapper.user.UserSubjectUiMapper
import com.space.navigation_api.AppNavigator
import com.space.quiz_api.QuizNavigator
import kotlinx.coroutines.Dispatchers.IO

class PointsViewModel(
    private val readUserSubjectsUC: ReadUserSubjectsUseCase,
    private val userSubjectsMapper: UserSubjectUiMapper,
    private val saveUserTokenUC: SaveUserTokenUseCase,
    private val appNavigator: AppNavigator,
    private val quizNavigator: QuizNavigator
) : BaseViewModel() {

    val userSubjects by LiveDataDelegate<List<UserSubjectUiModel>?>(
        null
    )

    fun getUserSubjects() {
        executeAsync(IO) {
            userSubjects.post(userSubjectsMapper.toUiList(readUserSubjectsUC()))
        }
    }

    fun logOut() {
        executeAsync(IO) {
            saveUserTokenUC(EMPTY_STRING)
            appNavigator.navigateToStart()
        }
    }

    fun navigateToHome() {
        appNavigator.navigateToHome()
    }

    fun navigateToQuestion(quizTitle: String) {
        quizNavigator.navigateToQuiz(bundleOf(QuizNavigator.TAG to quizTitle))
    }
}
