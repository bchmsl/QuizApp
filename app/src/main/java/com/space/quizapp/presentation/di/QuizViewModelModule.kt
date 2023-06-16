package com.space.quizapp.presentation.di

import com.space.quizapp.domain.di.QuizUseCaseNames
import com.space.quizapp.presentation.model.quiz.mapper.answer.QuizAnswerDomainUiMapper
import com.space.quizapp.presentation.model.quiz.mapper.answer.QuizAnswerUiDomainMapper
import com.space.quizapp.presentation.model.quiz.mapper.question.QuizQuestionDomainUiMapper
import com.space.quizapp.presentation.model.quiz.mapper.subject.QuizSubjectDomainUiMapper
import com.space.quizapp.presentation.model.user.mapper.user.user.QuizUserDomainUiMapper
import com.space.quizapp.presentation.model.user.mapper.user.user.QuizUserUiDomainMapper
import com.space.quizapp.presentation.ui.ui_home.vm.QuizHomeViewModel
import com.space.quizapp.presentation.ui.ui_points.vm.QuizPointsViewModel
import com.space.quizapp.presentation.ui.ui_question.vm.QuizQuestionViewModel
import com.space.quizapp.presentation.ui.ui_start.vm.QuizStartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        QuizStartViewModel(
            get(QuizUseCaseNames.SAVE_USER_DATA),
            get(QuizUseCaseNames.READ_USER_TOKEN),
            QuizUserUiDomainMapper()
        )
    }
    viewModel {
        QuizHomeViewModel(
            get(QuizUseCaseNames.READ_USER_DATA),
            get(QuizUseCaseNames.SAVE_USER_TOKEN),
            get(QuizUseCaseNames.RETRIEVE_SUBJECTS),
            QuizUserDomainUiMapper(),
            QuizSubjectDomainUiMapper()
        )
    }
    viewModel {
        QuizQuestionViewModel(
            get(QuizUseCaseNames.GET_NEXT_QUESTION),
            get(QuizUseCaseNames.CHECK_ANSWERS),
            get(QuizUseCaseNames.RETRIEVE_QUESTIONS),
            get(QuizUseCaseNames.GET_POINTS),
            QuizQuestionDomainUiMapper(QuizAnswerDomainUiMapper()),
            QuizAnswerUiDomainMapper(),
            QuizAnswerDomainUiMapper()

        )
    }
    viewModel {
        QuizPointsViewModel()
    }
}
