package com.space.quiz_impl.domain

import com.space.quiz_impl.domain.usecase.FinishAlertUseCase
import com.space.quiz_impl.domain.usecase.GetQuestionsCountUseCase
import com.space.quiz_impl.domain.usecase.QuizCheckAnswersUseCase
import com.space.quiz_impl.domain.usecase.QuizGetNextQuestionUseCase
import com.space.quiz_impl.domain.usecase.QuizSaveUserPointsUseCase
import com.space.quiz_impl.domain.usecase.QuizUpdateGpaUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { QuizUpdateGpaUseCase(updateUserGpa = get()) }
    single { QuizSaveUserPointsUseCase(saveUserPoints = get()) }
    single { QuizGetNextQuestionUseCase(getNextQuestion = get()) }
    single { QuizCheckAnswersUseCase(getNextQuestion = get(), updateQuestion = get()) }
    single { GetQuestionsCountUseCase(getQuestionsCount = get()) }
    single { FinishAlertUseCase() }
}