package com.space.quizapp.presentation.di

import com.space.quizapp.presentation.model.quiz.mapper.QuizAnswerUiMapper
import com.space.quizapp.presentation.model.quiz.mapper.QuizQuestionUiMapper
import com.space.quizapp.presentation.model.quiz.mapper.QuizSubjectUiMapper
import com.space.quizapp.presentation.model.user.mapper.user.QuizUserSubjectUiMapper
import com.space.quizapp.presentation.model.user.mapper.user.QuizUserUiMapper
import org.koin.dsl.module

val uiMapperModule = module {
    single { QuizAnswerUiMapper() }
    single { QuizQuestionUiMapper(answerUiMapper = get()) }
    single { QuizSubjectUiMapper() }
    single { QuizUserSubjectUiMapper() }
    single { QuizUserUiMapper() }
}
