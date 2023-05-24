package com.space.quizapp.di.module

import com.space.quizapp.domain.usecase.readuser.RetrieveUserUseCase
import com.space.quizapp.domain.usecase.readuser.RetrieveUserUseCaseImpl
import com.space.quizapp.domain.usecase.saveuser.SaveUserUseCase
import com.space.quizapp.domain.usecase.saveuser.SaveUserUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<SaveUserUseCase> { SaveUserUseCaseImpl() }
    single<RetrieveUserUseCase> { RetrieveUserUseCaseImpl() }
}
