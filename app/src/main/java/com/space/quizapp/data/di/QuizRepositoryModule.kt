package com.space.quizapp.data.di

import com.space.quizapp.data.repository.quiz.QuizQuestionsRepositoryImpl
import com.space.quizapp.data.repository.quiz.QuizSubjectsRepositoryImpl
import com.space.quizapp.data.repository.user.QuizUserDataRepositoryImpl
import com.space.quizapp.data.repository.user.QuizUserSubjectRepositoryImpl
import com.space.quizapp.data.repository.user.QuizUserTokenRepositoryImpl
import com.space.quizapp.domain.repository.quiz.QuizQuestionsRepository
import com.space.quizapp.domain.repository.quiz.QuizSubjectsRepository
import com.space.quizapp.domain.repository.user.QuizUserDataRepository
import com.space.quizapp.domain.repository.user.QuizUserSubjectRepository
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
            subjectsDao = get()
        )
    }
    single<QuizQuestionsRepository> {
        QuizQuestionsRepositoryImpl(
            questionsDao = get(),
            questionEntityMapper = get()
        )
    }


}
