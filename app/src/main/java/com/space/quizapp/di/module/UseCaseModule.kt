package com.space.quizapp.di.module

import com.space.quizapp.domain.usecase.user.read_user_data.RetrieveUserDataDataUseCaseImpl
import com.space.quizapp.domain.usecase.user.read_user_data.RetrieveUserDataUseCase
import com.space.quizapp.domain.usecase.user.read_user_token.ReadUserTokenUseCase
import com.space.quizapp.domain.usecase.user.read_user_token.ReadUserTokenUseCaseImpl
import com.space.quizapp.domain.usecase.user.save_user_data.SaveUserDataUseCase
import com.space.quizapp.domain.usecase.user.save_user_data.SaveUserDataUseCaseImpl
import com.space.quizapp.domain.usecase.user.save_user_token.SaveUserTokenUseCase
import com.space.quizapp.domain.usecase.user.save_user_token.SaveUserTokenUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<SaveUserDataUseCase> { SaveUserDataUseCaseImpl(get()) }
    single<RetrieveUserDataUseCase> { RetrieveUserDataDataUseCaseImpl(get()) }
    single<SaveUserTokenUseCase> { SaveUserTokenUseCaseImpl() }
    single<ReadUserTokenUseCase> { ReadUserTokenUseCaseImpl() }
}
