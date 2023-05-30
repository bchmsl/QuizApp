package com.space.quizapp.di.module

import com.space.quizapp.data.local.database.model.mapper.QuizUserDomainEntityMapper
import com.space.quizapp.data.local.database.model.mapper.QuizUserEntityDomainMapper
import com.space.quizapp.data.remote.model.QuizQuestionDtoDomainMapper
import com.space.quizapp.data.remote.model.QuizQuestionsDtoDomainMapper
import com.space.quizapp.data.repository.QuizQuestionsRepositoryImpl
import com.space.quizapp.data.repository.QuizUserDataRepositoryImpl
import com.space.quizapp.data.repository.QuizUserTokenRepositoryImpl
import com.space.quizapp.domain.repository.QuizQuestionsRepository
import com.space.quizapp.domain.repository.QuizUserDataRepository
import com.space.quizapp.domain.repository.QuizUserTokenRepository
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
