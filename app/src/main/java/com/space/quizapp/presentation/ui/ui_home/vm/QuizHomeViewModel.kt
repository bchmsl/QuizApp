package com.space.quizapp.presentation.ui.ui_home.vm

import com.space.common.base.viewmodel.QuizBaseViewModel
import com.space.common.extensions.coroutines.executeAsync
import com.space.common.model.subject.presentation.QuizSubjectUiMapper
import com.space.common.model.subject.presentation.QuizSubjectUiModel
import com.space.common.util.QuizConstants.EMPTY_STRING
import com.space.common.util.QuizCustomThrowable
import com.space.common.util.QuizLiveDataDelegate
import com.space.common.util.S
import com.space.quizapp.domain.usecase.quiz.QuizRetrieveSubjectsUseCase
import com.space.quizapp.domain.usecase.user.QuizReadUserDataUseCase
import com.space.quizapp.domain.usecase.user.QuizSaveUserTokenUseCase
import com.space.quizapp.presentation.model.user.QuizUserUiModel
import com.space.quizapp.presentation.model.user.mapper.user.QuizUserUiMapper
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections
import kotlinx.coroutines.Dispatchers.IO

class QuizHomeViewModel(
    private val readUserDataUC: QuizReadUserDataUseCase,
    private val saveUserTokenUC: QuizSaveUserTokenUseCase,
    private val retrieveSubjectsUC: QuizRetrieveSubjectsUseCase,
    private val userMapper: QuizUserUiMapper,
    private val subjectMapper: com.space.common.model.subject.presentation.QuizSubjectUiMapper,
) : com.space.common.base.viewmodel.QuizBaseViewModel() {

    val userState by com.space.common.util.QuizLiveDataDelegate<QuizUserUiModel?>(null)
    val subjectsState by com.space.common.util.QuizLiveDataDelegate<List<com.space.common.model.subject.presentation.QuizSubjectUiModel>?>(null)
    val loadingState by com.space.common.util.QuizLiveDataDelegate(true)

    fun retrieveUserInfo() {
        executeAsync(IO) {
            try {
                userState.post(userMapper.toUi(readUserDataUC()))
            } catch (e: Throwable) {
                postError(com.space.common.util.QuizCustomThrowable(e.message))
            }
        }
    }

    fun retrieveSubjects() {
        executeAsync(IO) {
            val data = retrieveSubjectsUC()
            if (data.isEmpty()) {
                postError(com.space.common.util.QuizCustomThrowable(com.space.common.util.S.error_bad_request))
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

    fun navigateToQuestion(subjectTitle: String) {
        navigate(QuizFragmentDirections.QUESTION, subjectTitle)
    }

    fun navigateToPoints() {
        navigate(QuizFragmentDirections.POINTS)
    }
}