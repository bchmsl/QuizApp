package com.space.quizapp.di.module

import com.space.quizapp.presentation.common.model.mapper.QuizUserDomainUiMapper
import com.space.quizapp.presentation.common.model.mapper.QuizUserUiDomainMapper
import com.space.quizapp.presentation.ui.home.vm.QuizHomeViewModel
import com.space.quizapp.presentation.ui.start.vm.QuizStartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { QuizStartViewModel(get(), get(), QuizUserUiDomainMapper()) }
    viewModel { QuizHomeViewModel(get(), get(), QuizUserDomainUiMapper()) }
}
