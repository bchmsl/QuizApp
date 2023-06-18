package com.space.quizapp.data.di

import com.space.quizapp.data.repository.quiz.QuizRepositoryImpl
import com.space.quizapp.data.repository.user.QuizUserDataRepositoryImpl
import com.space.quizapp.data.repository.user.QuizUserTokenRepositoryImpl
import com.space.quizapp.domain.repository.quiz.QuizRepository
import com.space.quizapp.domain.repository.user.QuizUserDataRepository
import com.space.quizapp.domain.repository.user.QuizUserTokenRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<QuizUserDataRepository> {
        QuizUserDataRepositoryImpl(
            userDao = get(),
            userMapper = get()
        )
    }
    single<QuizUserTokenRepository> {
        QuizUserTokenRepositoryImpl(
            userDataStoreManager = get()
        )
    }
    single<QuizRepository> {
        QuizRepositoryImpl(
            questionsApi = get(),
            subjectsDao = get(),
            subjectDtoMapper = get(),
            questionDtoMapper = get(),
            subjectEntityMapper = get(),
            questionEntityMapper = get()
        )

    }
}
