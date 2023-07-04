package com.space.quizapp.data.di

import com.space.quizapp.data.local.database.model.quiz.mapper.QuizQuestionEntityMapper
import com.space.quizapp.data.local.database.model.quiz.mapper.QuizSubjectEntityMapper
import com.space.quizapp.data.local.database.model.user.mapper.QuizUserEntityMapper
import com.space.quizapp.data.local.database.model.user.mapper.QuizUserSubjectEntityMapper
import com.space.quizapp.data.remote.model.mapper.QuizQuestionDtoMapper
import com.space.quizapp.data.remote.model.mapper.QuizSubjectDtoMapper
import org.koin.dsl.module

val dataMapperModule = module {
    single { QuizQuestionEntityMapper() }
    single { QuizSubjectEntityMapper() }
    single { QuizUserEntityMapper() }
    single { QuizQuestionDtoMapper() }
    single { QuizSubjectDtoMapper() }
    single { QuizUserSubjectEntityMapper() }
}
