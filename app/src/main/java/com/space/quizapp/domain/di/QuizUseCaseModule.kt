package com.space.quizapp.domain.di

import com.space.quizapp.common.util.QuizValidateUser
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel
import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.domain.model.user.QuizUserSubjectDomainModel
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
import com.space.quizapp.domain.usecase.user.QuizUpdateGpaUseCase
import com.space.quizapp.domain.usecase.user.read_user_data.QuizReadUserDataUseCase
import com.space.quizapp.domain.usecase.user.subject.QuizReadUserSubjectsUseCase
import com.space.quizapp.domain.usecase.user.subject.QuizSaveUserSubjectUseCase
import com.space.quizapp.presentation.ui.ui_question.manager.QuizManager
import com.space.quizapp.presentation.ui.ui_question.manager.QuizManagerImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<QuizBaseUseCase<QuizUserDomainModel, QuizValidateUser>>(
        QuizUseCaseNames.SAVE_USER_DATA
    ) {
        QuizSaveUserDataUseCase(
            saveUserToken = get(QuizUseCaseNames.SAVE_USER_TOKEN),
            repository = get()
        )
    }

    single<QuizBaseUseCase<Unit, QuizUserDomainModel>>(
        QuizUseCaseNames.READ_USER_DATA
    ) {
        QuizReadUserDataUseCase(
            readUserToken = get(QuizUseCaseNames.READ_USER_TOKEN),
            repository = get()
        )
    }

    single<QuizBaseUseCase<String, Unit>>(
        QuizUseCaseNames.SAVE_USER_TOKEN
    ) {
        QuizSaveUserTokenUseCase(
            repository = get()
        )
    }

    single<QuizBaseUseCase<Unit, String>>(
        QuizUseCaseNames.READ_USER_TOKEN
    ) {
        QuizReadUserTokenUseCase(
            repository = get()
        )
    }


    single<QuizBaseUseCase<QuizQuestionDomainModel.QuizAnswerDomainModel, List<QuizQuestionDomainModel.QuizAnswerDomainModel>>>(
        QuizUseCaseNames.CHECK_ANSWERS
    ) {
        QuizCheckAnswersUseCase(
            manager = get()
        )
    }

    single<QuizBaseUseCase<Unit, QuizGetNextQuestionResponse<QuizQuestionDomainModel?>>>(
        QuizUseCaseNames.GET_NEXT_QUESTION
    ) {
        QuizGetNextQuestionUseCase(
            manager = get()
        )
    }

    single<QuizBaseUseCase<Int, Unit>>(
        QuizUseCaseNames.RETRIEVE_QUESTIONS
    ) {
        QuizRetrieveQuestionsUseCase(
            repository = get(),
            manager = get()
        )
    }

    single<QuizBaseUseCase<Unit, List<QuizSubjectDomainModel>>>(
        QuizUseCaseNames.RETRIEVE_SUBJECTS
    ) {
        QuizRetrieveSubjectsUseCase(
            repository = get()
        )
    }

    single<QuizBaseUseCase<Unit, Int>>(
        QuizUseCaseNames.GET_POINTS
    ) {
        QuizGetPointsUseCase(
            manager = get()
        )
    }

    single<QuizBaseUseCase<Unit, List<QuizUserSubjectDomainModel>>>(
        QuizUseCaseNames.READ_USER_SUBJECTS
    ) {
        QuizReadUserSubjectsUseCase(
            readUserDataUC = get(QuizUseCaseNames.READ_USER_DATA),
            userSubjectRepository = get(),
            subjectRepository = get()
        )
    }

    single<QuizBaseUseCase<QuizUserSubjectDomainModel, Unit>>(
        QuizUseCaseNames.SAVE_USER_SUBJECT
    ) {
        QuizSaveUserSubjectUseCase(
            repository = get(),
            readUserData = get(QuizUseCaseNames.READ_USER_DATA)
        )
    }

    single<QuizBaseUseCase<Unit, Unit>>(
        QuizUseCaseNames.UPDATE_GPA
    ) {
        QuizUpdateGpaUseCase(
            getUserTokenUseCase = get(QuizUseCaseNames.READ_USER_TOKEN),
            readUserSubjectsUseCase = get(QuizUseCaseNames.READ_USER_SUBJECTS),
            subjectsRepository = get(),
            userDataRepository = get()
        )
    }

    single<QuizManager> { QuizManagerImpl() }
}
