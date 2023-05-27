package com.space.quizapp.di.module

import com.space.quizapp.data.local.database.model.mapper.UserDomainEntityMapper
import com.space.quizapp.data.local.database.model.mapper.UserEntityDomainMapper
import com.space.quizapp.data.repository.UserDataRepositoryImpl
import com.space.quizapp.data.repository.UserTokenTokenRepositoryImpl
import com.space.quizapp.domain.repository.UserDataRepository
import com.space.quizapp.domain.repository.UserTokenRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UserDataRepository> {
        UserDataRepositoryImpl(
            get(),
            UserDomainEntityMapper(),
            UserEntityDomainMapper()
        )
    }
    single<UserTokenRepository> {
        UserTokenTokenRepositoryImpl(
            get()
        )
    }
}
