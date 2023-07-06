package com.space.main_impl.data.di

import com.space.common.model.question.data.QuizQuestionEntityMapper
import com.space.common.model.subject.data.QuizSubjectEntityMapper
import com.space.main_impl.data.local.database.model.user.mapper.QuizUserEntityMapper
import com.space.main_impl.data.local.database.model.user.mapper.QuizUserSubjectEntityMapper
import com.space.main_impl.data.remote.model.mapper.QuizQuestionDtoMapper
import com.space.main_impl.data.remote.model.mapper.QuizSubjectDtoMapper
import org.koin.dsl.module

val dataMapperModule = module {
    single { QuizQuestionEntityMapper() }
    single { QuizSubjectEntityMapper() }
    single { QuizUserEntityMapper() }
    single { QuizQuestionDtoMapper() }
    single { QuizSubjectDtoMapper() }
    single { QuizUserSubjectEntityMapper() }
}
