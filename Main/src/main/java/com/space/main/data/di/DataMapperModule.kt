package com.space.main.data.di

import com.space.common.model.question.data.QuestionEntityMapper
import com.space.common.model.subject.data.SubjectEntityMapper
import com.space.main.data.local.database.model.user.mapper.UserEntityMapper
import com.space.main.data.local.database.model.user.mapper.UserSubjectEntityMapper
import com.space.main.data.remote.model.mapper.QuestionDtoMapper
import com.space.main.data.remote.model.mapper.SubjectDtoMapper
import org.koin.dsl.module

val dataMapperModule = module {
    single { QuestionEntityMapper() }
    single { SubjectEntityMapper() }
    single { UserEntityMapper() }
    single { QuestionDtoMapper() }
    single { SubjectDtoMapper() }
    single { UserSubjectEntityMapper() }
}
