package com.space.quiz_impl.domain

import com.space.quiz_impl.domain.usecase.CheckAnswersUseCase
import com.space.quiz_impl.domain.usecase.FinishAlertUseCase
import com.space.quiz_impl.domain.usecase.GetNextQuestionUseCase
import com.space.quiz_impl.domain.usecase.GetQuestionsCountUseCase
import com.space.quiz_impl.domain.usecase.SaveUserPointsUseCase
import com.space.quiz_impl.domain.usecase.UpdateGpaUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { UpdateGpaUseCase(updateUserGpa = get()) }
    single { SaveUserPointsUseCase(saveUserPoints = get()) }
    single { GetNextQuestionUseCase(getNextQuestion = get()) }
    single { CheckAnswersUseCase(getNextQuestion = get(), updateQuestion = get()) }
    single { GetQuestionsCountUseCase(getQuestionsCount = get()) }
    single { FinishAlertUseCase() }
}