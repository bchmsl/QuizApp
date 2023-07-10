package com.space.main.data.di

import com.space.main.data.repository.quiz.QuizQuestionsRepositoryImpl
import com.space.main.data.repository.quiz.QuizSubjectsRepositoryImpl
import com.space.main.data.repository.user.QuizUserDataRepositoryImpl
import com.space.main.data.repository.user.QuizUserSubjectRepositoryImpl
import com.space.main.data.repository.user.QuizUserTokenRepositoryImpl
import com.space.main.domain.repository.quiz.QuizQuestionsRepository
import com.space.main.domain.repository.quiz.QuizSubjectsRepository
import com.space.main.domain.repository.user.QuizUserDataRepository
import com.space.main.domain.repository.user.QuizUserSubjectRepository
import com.space.main.domain.repository.user.QuizUserTokenRepository
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
    single<QuizSubjectsRepository> {
        QuizSubjectsRepositoryImpl(
            questionsApi = get(),
            subjectsDao = get(),
            subjectDtoMapper = get(),
            questionDtoMapper = get(),
            subjectEntityMapper = get(),
            questionEntityMapper = get(),
            questionsDao = get()
        )
    }
    single<QuizUserSubjectRepository> {
        QuizUserSubjectRepositoryImpl(
            userSubjectsDao = get(),
            userSubjectEntityMapper = get(),
            subjectsDao = get(),
            questionsDao = get()
        )
    }
    single<QuizQuestionsRepository> {
        QuizQuestionsRepositoryImpl(
            questionsDao = get(),
            questionEntityMapper = get()
        )
    }


}
