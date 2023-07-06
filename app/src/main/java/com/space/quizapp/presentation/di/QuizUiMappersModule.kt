package com.space.quizapp.presentation.di

import com.space.common.model.question.presentation.QuizAnswerUiMapper
import com.space.common.model.question.presentation.QuizQuestionUiMapper
import com.space.common.model.subject.presentation.QuizSubjectUiMapper
import com.space.quizapp.presentation.model.user.mapper.user.QuizUserSubjectUiMapper
import com.space.quizapp.presentation.model.user.mapper.user.QuizUserUiMapper
import org.koin.dsl.module

val uiMapperModule = module {
    single { com.space.common.model.question.presentation.QuizAnswerUiMapper() }
    single { com.space.common.model.question.presentation.QuizQuestionUiMapper(answerUiMapper = get()) }
    single { com.space.common.model.subject.presentation.QuizSubjectUiMapper() }
    single { QuizUserSubjectUiMapper() }
    single { QuizUserUiMapper() }
}
