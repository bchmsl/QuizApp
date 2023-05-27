package com.space.quizapp.di.module

import com.space.quizapp.presentation.common.model.mapper.UserUiDomainMapper
import com.space.quizapp.presentation.ui.start.vm.StartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { StartViewModel(get(), get(), UserUiDomainMapper()) }
}
