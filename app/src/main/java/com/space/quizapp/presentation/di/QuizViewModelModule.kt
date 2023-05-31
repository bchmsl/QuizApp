package com.space.quizapp.presentation.di

import com.space.quizapp.presentation.model.quiz.mapper.QuizQuestionDomainUiMapper
import com.space.quizapp.presentation.model.quiz.mapper.QuizQuestionsDomainUiMapper
import com.space.quizapp.presentation.model.user.mapper.QuizUserDomainUiMapper
import com.space.quizapp.presentation.model.user.mapper.QuizUserUiDomainMapper
import com.space.quizapp.presentation.ui.ui_home.vm.QuizHomeViewModel
import com.space.quizapp.presentation.ui.ui_question.vm.QuizQuestionViewModel
import com.space.quizapp.presentation.ui.ui_start.vm.QuizStartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { QuizStartViewModel(get(), get(), QuizUserUiDomainMapper()) }
    viewModel {
        QuizHomeViewModel(
            get(), get(), QuizUserDomainUiMapper(), QuizQuestionsDomainUiMapper(
                QuizQuestionDomainUiMapper()
            ), get()
        )
    }
    viewModel {
        QuizQuestionViewModel(get(), QuizQuestionDomainUiMapper())
    }
}
