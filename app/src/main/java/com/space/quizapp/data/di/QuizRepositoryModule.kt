package com.space.quizapp.data.di

import com.space.quizapp.data.local.database.model.user.mapper.subject.QuizUserSubjectDomainEntityMapper
import com.space.quizapp.data.local.database.model.user.mapper.subject.QuizUserSubjectEntityDomainMapper
import com.space.quizapp.data.local.database.model.user.mapper.user.QuizUserDomainEntityMapper
import com.space.quizapp.data.local.database.model.user.mapper.user.QuizUserEntityDomainMapper
import com.space.quizapp.data.remote.model.mapper.QuizQuestionDtoDomainMapper
import com.space.quizapp.data.remote.model.mapper.QuizQuestionsDtoDomainMapper
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
            get(),
            QuizUserDomainEntityMapper(QuizUserSubjectDomainEntityMapper()),
            QuizUserEntityDomainMapper(QuizUserSubjectEntityDomainMapper())
        )
    }
    single<QuizUserTokenRepository> {
        QuizUserTokenRepositoryImpl(
            get()
        )
    }
    single<QuizRepository> {
        QuizRepositoryImpl(
            get(),
            QuizQuestionsDtoDomainMapper(QuizQuestionDtoDomainMapper())
        )
    }
}
