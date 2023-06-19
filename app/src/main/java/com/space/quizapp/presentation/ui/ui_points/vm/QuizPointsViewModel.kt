package com.space.quizapp.presentation.ui.ui_points.vm

import com.space.quizapp.common.extensions.coroutines.executeAsync
import com.space.quizapp.domain.usecase.user.subject.QuizReadUserSubjectsUseCase
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.model.user.QuizUserSubjectUiModel
import com.space.quizapp.presentation.model.user.mapper.user.QuizUserSubjectUiMapper
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuizPointsViewModel(
    private val readUserSubjectsUC: QuizReadUserSubjectsUseCase,
    private val userSubjectsMapper: QuizUserSubjectUiMapper
) : QuizBaseViewModel() {

    private val _userSubjects = MutableStateFlow<List<QuizUserSubjectUiModel>?>(null)
    val userSubjects get() = _userSubjects.asStateFlow()

    fun getUserSubjects() {
        executeAsync(IO) {
            _userSubjects.emit(
                userSubjectsMapper.toUiList(readUserSubjectsUC())
            )
        }
    }

}
