package com.space.quizapp.presentation.ui.ui_points.vm

import com.space.quizapp.common.extensions.coroutines.executeAsync
import com.space.quizapp.common.util.QuizLiveDataDelegate
import com.space.quizapp.domain.usecase.user.subject.QuizReadUserSubjectsUseCase
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.model.user.QuizUserSubjectUiModel
import com.space.quizapp.presentation.model.user.mapper.user.QuizUserSubjectUiMapper
import kotlinx.coroutines.Dispatchers.IO

class QuizPointsViewModel(
    private val readUserSubjectsUC: QuizReadUserSubjectsUseCase,
    private val userSubjectsMapper: QuizUserSubjectUiMapper
) : QuizBaseViewModel() {

    val userSubjects by QuizLiveDataDelegate<List<QuizUserSubjectUiModel>?>(null)

    fun getUserSubjects() {
        executeAsync(IO) {
            userSubjects.post(userSubjectsMapper.toUiList(readUserSubjectsUC()))
        }
    }

}
