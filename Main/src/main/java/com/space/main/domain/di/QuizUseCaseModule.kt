package com.space.main.domain.di

import com.space.main.domain.usecase.questions.QuizSaveUserPointsUseCase
import com.space.main.domain.usecase.questions.QuizUpdateGpaUseCase
import com.space.main.domain.usecase.questions.QuizUpdateQuestionUseCase
import com.space.main.domain.usecase.quiz.GetNextQuestionUseCase
import com.space.main.domain.usecase.quiz.GetQuestionsCountUseCase
import com.space.main.domain.usecase.quiz.QuizRetrieveSubjectsUseCase
import com.space.main.domain.usecase.user.QuizReadUserDataUseCase
import com.space.main.domain.usecase.user.QuizReadUserTokenUseCase
import com.space.main.domain.usecase.user.QuizSaveUserDataUseCase
import com.space.main.domain.usecase.user.QuizSaveUserTokenUseCase
import com.space.main.domain.usecase.user.ValidateUserUseCase
import com.space.main.domain.usecase.user.subject.QuizReadUserSubjectsUseCase
import com.space.quiz_api.GetNextQuestion
import com.space.quiz_api.GetQuestionsCount
import com.space.quiz_api.SaveUserPoints
import com.space.quiz_api.UpdateQuestion
import com.space.quiz_api.UpdateUserGpa
import org.koin.dsl.module


val useCaseModule = module {
    single {
        QuizSaveUserDataUseCase(
            saveUserTokenUC = get(),
            userDataRepository = get()
        )
    }

    single {
        QuizReadUserDataUseCase(
            readUserTokenUC = get(),
            userDataRepository = get()
        )
    }

    single {
        QuizSaveUserTokenUseCase(
            userTokenRepository = get()
        )
    }

    single {
        QuizReadUserTokenUseCase(
            userTokenRepository = get()
        )
    }

    single<GetNextQuestion> {
        GetNextQuestionUseCase(
            repository = get()
        )
    }

    single {
        QuizRetrieveSubjectsUseCase(
            subjectsRepository = get()
        )
    }

    single {
        QuizReadUserSubjectsUseCase(
            readUserDataUC = get(),
            userSubjectRepository = get(),
            subjectRepository = get()
        )
    }

    single<UpdateUserGpa> {
        QuizUpdateGpaUseCase(
            readUserSubjectsUC = get(),
            subjectsRepository = get(),
            userDataRepository = get(),
            readUserDataUC = get(),
            questionsRepository = get()
        )
    }

    single<SaveUserPoints> {
        QuizSaveUserPointsUseCase(
            readUserDataUC = get(),
            userSubjectRepository = get()
        )
    }

    single<GetQuestionsCount> {
        GetQuestionsCountUseCase(
            quizRepository = get()
        )
    }
    single {
        ValidateUserUseCase()
    }
    single<UpdateQuestion> {
        QuizUpdateQuestionUseCase(get())
    }
}
