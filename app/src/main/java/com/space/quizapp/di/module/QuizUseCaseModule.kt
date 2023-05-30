package com.space.quizapp.di.module

import com.space.quizapp.domain.usecase.questions.questions.QuizQuestionsUseCase
import com.space.quizapp.domain.usecase.questions.questions.QuizQuestionsUseCaseImpl
import com.space.quizapp.domain.usecase.user.read_user_data.QuizRetrieveUserDataUseCase
import com.space.quizapp.domain.usecase.user.read_user_data.RetrieveUserDataDataUseCaseImpl
import com.space.quizapp.domain.usecase.user.read_user_token.QuizReadUserTokenUseCase
import com.space.quizapp.domain.usecase.user.read_user_token.QuizReadUserTokenUseCaseImpl
import com.space.quizapp.domain.usecase.user.save_user_data.QuizSaveUserDataUseCase
import com.space.quizapp.domain.usecase.user.save_user_data.QuizSaveUserDataUseCaseImpl
import com.space.quizapp.domain.usecase.user.save_user_token.QuizSaveUserTokenUseCase
import com.space.quizapp.domain.usecase.user.save_user_token.QuizSaveUserTokenUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<QuizSaveUserDataUseCase> { QuizSaveUserDataUseCaseImpl(get()) }
    single<QuizRetrieveUserDataUseCase> { RetrieveUserDataDataUseCaseImpl(get()) }
    single<QuizSaveUserTokenUseCase> { QuizSaveUserTokenUseCaseImpl() }
    single<QuizReadUserTokenUseCase> { QuizReadUserTokenUseCaseImpl() }
    single<QuizQuestionsUseCase> { QuizQuestionsUseCaseImpl() }
}
