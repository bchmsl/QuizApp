package com.space.quizapp.di.module

import com.space.quizapp.data.local.database.model.mapper.UserDomainEntityMapper
import com.space.quizapp.data.local.database.model.mapper.UserEntityDomainMapper
import com.space.quizapp.data.repository.UserRepositoryImpl
import com.space.quizapp.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> {
        UserRepositoryImpl(
            get(),
            UserDomainEntityMapper(),
            UserEntityDomainMapper()
        )
    }
}
