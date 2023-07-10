package com.space.main.presentation.ui.ui_points.vm

import androidx.core.os.bundleOf
import com.space.common.base.viewmodel.QuizBaseViewModel
import com.space.common.extensions.coroutines.executeAsync
import com.space.common.util.QuizConstants.EMPTY_STRING
import com.space.common.util.QuizLiveDataDelegate
import com.space.main.domain.usecase.user.QuizSaveUserTokenUseCase
import com.space.main.domain.usecase.user.subject.QuizReadUserSubjectsUseCase
import com.space.main.presentation.model.user.QuizUserSubjectUiModel
import com.space.main.presentation.model.user.mapper.user.QuizUserSubjectUiMapper
import com.space.navigation_api.AppNavigator
import com.space.quiz_api.QuizNavigator
import kotlinx.coroutines.Dispatchers.IO

class QuizPointsViewModel(
    private val readUserSubjectsUC: QuizReadUserSubjectsUseCase,
    private val userSubjectsMapper: QuizUserSubjectUiMapper,
    private val saveUserTokenUC: QuizSaveUserTokenUseCase,
    private val appNavigator: AppNavigator,
    private val quizNavigator: QuizNavigator
) : QuizBaseViewModel() {

    val userSubjects by QuizLiveDataDelegate<List<QuizUserSubjectUiModel>?>(
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
