package com.space.quizapp.presentation.di

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
            get(),
            get(),
            QuizUserUiDomainMapper()
        )
    }
    viewModel {
        QuizHomeViewModel(
            get(),
            get(),
            QuizUserDomainUiMapper(),
            QuizSubjectDomainUiMapper(),
            get()
        )
    }
    viewModel {
        QuizQuestionViewModel(
            get(),
            get(),
            get(),
            QuizQuestionDomainUiMapper(QuizAnswerDomainUiMapper()),
            QuizAnswerUiDomainMapper(),
            QuizAnswerDomainUiMapper()

        )
    }
    viewModel {
        QuizPointsViewModel()
    }
}
