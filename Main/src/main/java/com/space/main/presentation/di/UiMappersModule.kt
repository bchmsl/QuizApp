package com.space.main.presentation.di

import com.space.common.model.question.model.AnswerUiMapper
import com.space.common.model.question.model.QuestionUiMapper
import com.space.common.model.question.model.SubjectUiMapper
import com.space.main.presentation.model.user.mapper.user.UserSubjectUiMapper
import com.space.main.presentation.model.user.mapper.user.UserUiMapper
import org.koin.dsl.module


val uiMapperModule = module {
    single { AnswerUiMapper() }
    single { QuestionUiMapper(answerUiMapper = get()) }
    single { SubjectUiMapper() }
    single { UserSubjectUiMapper() }
    single { UserUiMapper() }
}
