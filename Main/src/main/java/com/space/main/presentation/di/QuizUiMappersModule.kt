package com.space.main.presentation.di

import com.space.common.model.question.model.QuizAnswerUiMapper
import com.space.common.model.question.model.QuizQuestionUiMapper
import com.space.common.model.question.model.QuizSubjectUiMapper
import com.space.main.presentation.model.user.mapper.user.QuizUserSubjectUiMapper
import com.space.main.presentation.model.user.mapper.user.QuizUserUiMapper
import org.koin.dsl.module


val uiMapperModule = module {
    single { QuizAnswerUiMapper() }
    single { QuizQuestionUiMapper(answerUiMapper = get()) }
    single { QuizSubjectUiMapper() }
    single { QuizUserSubjectUiMapper() }
    single { QuizUserUiMapper() }
}
