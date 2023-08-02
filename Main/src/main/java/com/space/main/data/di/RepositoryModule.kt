package com.space.main.data.di

import com.space.main.data.repository.quiz.QuestionsRepositoryImpl
import com.space.main.data.repository.quiz.SubjectsRepositoryImpl
import com.space.main.data.repository.user.UserDataRepositoryImpl
import com.space.main.data.repository.user.UserSubjectRepositoryImpl
import com.space.main.data.repository.user.UserTokenRepositoryImpl
import com.space.main.domain.repository.quiz.QuestionsRepository
import com.space.main.domain.repository.quiz.SubjectsRepository
import com.space.main.domain.repository.user.UserDataRepository
import com.space.main.domain.repository.user.UserSubjectRepository
import com.space.main.domain.repository.user.UserTokenRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UserDataRepository> {
        UserDataRepositoryImpl(
            userDao = get(),
            userMapper = get()
        )
    }

    single<UserTokenRepository> {
        UserTokenRepositoryImpl(
            userDataStoreManager = get()
        )
    }

    single<SubjectsRepository> {
        SubjectsRepositoryImpl(
            questionsApi = get(),
            subjectsDao = get(),
            subjectDtoMapper = get(),
            questionDtoMapper = get(),
            subjectEntityMapper = get(),
            questionEntityMapper = get(),
            questionsDao = get()
        )
    }

    single<UserSubjectRepository> {
        UserSubjectRepositoryImpl(
            userSubjectsDao = get(),
            userSubjectEntityMapper = get(),
            subjectsDao = get(),
            questionsDao = get()
        )
    }

    single<QuestionsRepository> {
        QuestionsRepositoryImpl(
            questionsDao = get(),
            questionEntityMapper = get()
        )
    }
}
