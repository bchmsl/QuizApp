package com.space.quizapp.presentation.ui.ui_points.vm

import com.space.quizapp.common.extensions.coroutines.executeAsync
import com.space.quizapp.common.util.QuizConstants.EMPTY_STRING
import com.space.quizapp.common.util.QuizLiveDataDelegate
import com.space.quizapp.domain.usecase.user.QuizSaveUserTokenUseCase
import com.space.quizapp.domain.usecase.user.subject.QuizReadUserSubjectsUseCase
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.model.user.QuizUserSubjectUiModel
import com.space.quizapp.presentation.model.user.mapper.user.QuizUserSubjectUiMapper
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections
import kotlinx.coroutines.Dispatchers.IO

class QuizPointsViewModel(
    private val readUserSubjectsUC: QuizReadUserSubjectsUseCase,
    private val userSubjectsMapper: QuizUserSubjectUiMapper,
    private val saveUserTokenUC: QuizSaveUserTokenUseCase
) : QuizBaseViewModel() {

    val userSubjects by QuizLiveDataDelegate<List<QuizUserSubjectUiModel>?>(null)

    fun getUserSubjects() {
        executeAsync(IO) {
            userSubjects.post(userSubjectsMapper.toUiList(readUserSubjectsUC()))
        }
    }

    fun logOut() {
        executeAsync(IO) {
            saveUserTokenUC(EMPTY_STRING)
            navigate(QuizFragmentDirections.START)
        }
    }

    fun navigateToHome() {
        navigate(QuizFragmentDirections.HOME)
    }

    fun navigateToQuestion(questionTitle: String) {
        navigate(QuizFragmentDirections.QUESTION, questionTitle)
    }
}
