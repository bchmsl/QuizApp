package com.space.quizapp.data.di

import com.space.quizapp.data.local.database.model.mapper.QuizUserDomainEntityMapper
import com.space.quizapp.data.local.database.model.mapper.QuizUserEntityDomainMapper
import com.space.quizapp.data.remote.model.mapper.QuizQuestionDtoDomainMapper
import com.space.quizapp.data.remote.model.mapper.QuizQuestionsDtoDomainMapper
import com.space.quizapp.data.repository.quiz.QuizQuestionsRepositoryImpl
import com.space.quizapp.data.repository.user.QuizUserDataRepositoryImpl
import com.space.quizapp.data.repository.user.QuizUserTokenRepositoryImpl
import com.space.quizapp.domain.repository.quiz.QuizQuestionsRepository
import com.space.quizapp.domain.repository.user.QuizUserDataRepository
import com.space.quizapp.domain.repository.user.QuizUserTokenRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<QuizUserDataRepository> {
        QuizUserDataRepositoryImpl(
            get(),
            QuizUserDomainEntityMapper(),
            QuizUserEntityDomainMapper()
        )
    }
    single<QuizUserTokenRepository> {
        QuizUserTokenRepositoryImpl(
            get()
        )
    }
    single<QuizQuestionsRepository> {
        QuizQuestionsRepositoryImpl(
            get(),
            QuizQuestionsDtoDomainMapper(QuizQuestionDtoDomainMapper())
        )
    }
}
