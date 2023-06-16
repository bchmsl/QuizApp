package com.space.quizapp.domain.di

import com.space.quizapp.common.util.QuizValidateUser
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel
import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import com.space.quizapp.domain.usecase.questions.QuizCheckAnswersUseCase
import com.space.quizapp.domain.usecase.questions.QuizGetPointsUseCase
import com.space.quizapp.domain.usecase.questions.next_question.QuizGetNextQuestionResponse
import com.space.quizapp.domain.usecase.questions.next_question.QuizGetNextQuestionUseCase
import com.space.quizapp.domain.usecase.quiz.QuizRetrieveQuestionsUseCase
import com.space.quizapp.domain.usecase.quiz.QuizRetrieveSubjectsUseCase
import com.space.quizapp.domain.usecase.user.QuizReadUserTokenUseCase
import com.space.quizapp.domain.usecase.user.QuizSaveUserDataUseCase
import com.space.quizapp.domain.usecase.user.QuizSaveUserTokenUseCase
import com.space.quizapp.domain.usecase.user.read_user_data.QuizReadUserDataUseCase
import com.space.quizapp.presentation.ui.ui_question.manager.QuizManager
import com.space.quizapp.presentation.ui.ui_question.manager.QuizManagerImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<QuizBaseUseCase<QuizUserDomainModel, QuizValidateUser>>(
        QuizUseCaseNames.SAVE_USER_DATA
    ) {
        QuizSaveUserDataUseCase(
            get(QuizUseCaseNames.SAVE_USER_TOKEN),
            get()
        )
    }

    single<QuizBaseUseCase<Unit, QuizUserDomainModel>>(
        QuizUseCaseNames.READ_USER_DATA
    ) { QuizReadUserDataUseCase(get(QuizUseCaseNames.READ_USER_TOKEN), get()) }

    single<QuizBaseUseCase<String, Unit>>(
        QuizUseCaseNames.SAVE_USER_TOKEN
    ) { QuizSaveUserTokenUseCase(get()) }

    single<QuizBaseUseCase<Unit, String>>(
        QuizUseCaseNames.READ_USER_TOKEN
    ) { QuizReadUserTokenUseCase(get()) }


    single<QuizBaseUseCase<QuizQuestionDomainModel.QuizAnswerDomainModel, List<QuizQuestionDomainModel.QuizAnswerDomainModel>>>(
        QuizUseCaseNames.CHECK_ANSWERS
    ) { QuizCheckAnswersUseCase(get()) }

    single<QuizBaseUseCase<Unit, QuizGetNextQuestionResponse<QuizQuestionDomainModel>>>(
        QuizUseCaseNames.GET_NEXT_QUESTION
    ) { QuizGetNextQuestionUseCase(get()) }

    single<QuizBaseUseCase<Int, Unit>>(
        QuizUseCaseNames.RETRIEVE_QUESTIONS
    ) { QuizRetrieveQuestionsUseCase(get(), get()) }

    single<QuizBaseUseCase<Unit, List<QuizSubjectDomainModel>>>(
        QuizUseCaseNames.RETRIEVE_SUBJECTS
    ) { QuizRetrieveSubjectsUseCase(get()) }

    single<QuizBaseUseCase<Unit, Int>>(
        QuizUseCaseNames.GET_POINTS
    ) { QuizGetPointsUseCase(get()) }

    single<QuizManager> { QuizManagerImpl() }
}
