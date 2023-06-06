package com.space.quizapp.data.di

import com.space.quizapp.common.util.ApiHelper
import com.space.quizapp.data.local.database.model.quiz.mapper.question.QuizQuestionDomainEntityMapper
import com.space.quizapp.data.local.database.model.quiz.mapper.question.QuizQuestionEntityDomainMapper
import com.space.quizapp.data.local.database.model.quiz.mapper.subject.QuizSubjectDomainEntityMapper
import com.space.quizapp.data.local.database.model.quiz.mapper.subject.QuizSubjectEntityDomainMapper
import com.space.quizapp.data.local.database.model.user.mapper.user.QuizUserDomainEntityMapper
import com.space.quizapp.data.local.database.model.user.mapper.user.QuizUserEntityDomainMapper
import com.space.quizapp.data.remote.model.mapper.subject.QuizSubjectDtoDomainMapper
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
            QuizUserDomainEntityMapper(),
            QuizUserEntityDomainMapper()
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
            object : ApiHelper {},
            get(),
            QuizSubjectDtoDomainMapper(),
            QuizSubjectDomainEntityMapper(),
            QuizQuestionDomainEntityMapper(),
            QuizSubjectEntityDomainMapper(),
            QuizQuestionEntityDomainMapper()
        )

    }
}
