package com.space.main.domain.di

import com.space.main.domain.usecase.questions.SaveUserPointsUseCase
import com.space.main.domain.usecase.questions.UpdateGpaUseCase
import com.space.main.domain.usecase.questions.UpdateQuestionUseCase
import com.space.main.domain.usecase.quiz.GetNextQuestionUseCase
import com.space.main.domain.usecase.quiz.GetQuestionsCountUseCase
import com.space.main.domain.usecase.quiz.RetrieveSubjectsUseCase
import com.space.main.domain.usecase.user.ReadUserDataUseCase
import com.space.main.domain.usecase.user.ReadUserTokenUseCase
import com.space.main.domain.usecase.user.SaveUserDataUseCase
import com.space.main.domain.usecase.user.SaveUserTokenUseCase
import com.space.main.domain.usecase.user.ValidateUserUseCase
import com.space.main.domain.usecase.user.subject.ReadUserSubjectsUseCase
import com.space.quiz_api.GetNextQuestion
import com.space.quiz_api.GetQuestionsCount
import com.space.quiz_api.SaveUserPoints
import com.space.quiz_api.UpdateQuestion
import com.space.quiz_api.UpdateUserGpa
import org.koin.dsl.module


val useCaseModule = module {
    single {
        SaveUserDataUseCase(
            saveUserTokenUC = get(),
            userDataRepository = get()
        )
    }

    single {
        ReadUserDataUseCase(
            readUserTokenUC = get(),
            userDataRepository = get()
        )
    }

    single {
        SaveUserTokenUseCase(
            userTokenRepository = get()
        )
    }

    single {
        ReadUserTokenUseCase(
            userTokenRepository = get()
        )
    }

    single<GetNextQuestion> {
        GetNextQuestionUseCase(
            repository = get()
        )
    }

    single {
        RetrieveSubjectsUseCase(
            subjectsRepository = get()
        )
    }

    single {
        ReadUserSubjectsUseCase(
            readUserDataUC = get(),
            userSubjectRepository = get(),
            subjectRepository = get()
        )
    }

    single<UpdateUserGpa> {
        UpdateGpaUseCase(
            readUserSubjectsUC = get(),
            subjectsRepository = get(),
            userDataRepository = get(),
            readUserDataUC = get(),
            questionsRepository = get()
        )
    }

    single<SaveUserPoints> {
        SaveUserPointsUseCase(
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
        UpdateQuestionUseCase(get())
    }
}
