package com.space.main_impl.domain.di

import com.space.main_impl.domain.usecase.questions.QuizSaveUserPointsUseCase
import com.space.main_impl.domain.usecase.questions.QuizUpdateGpaUseCase
import com.space.main_impl.domain.usecase.quiz.GetNextQuestionUseCase
import com.space.main_impl.domain.usecase.quiz.GetQuestionsCountUseCase
import com.space.main_impl.domain.usecase.quiz.QuizRetrieveSubjectsUseCase
import com.space.main_impl.domain.usecase.user.QuizReadUserDataUseCase
import com.space.main_impl.domain.usecase.user.QuizReadUserTokenUseCase
import com.space.main_impl.domain.usecase.user.QuizSaveUserDataUseCase
import com.space.main_impl.domain.usecase.user.QuizSaveUserTokenUseCase
import com.space.main_impl.domain.usecase.user.ValidateUserUseCase
import com.space.main_impl.domain.usecase.user.subject.QuizReadUserSubjectsUseCase
import com.space.quiz_api.GetNextQuestion
import com.space.quiz_api.GetQuestionsCount
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

    single {
        QuizUpdateGpaUseCase(
            readUserSubjectsUC = get(),
            subjectsRepository = get(),
            userDataRepository = get(),
            readUserDataUC = get(),
            questionsRepository = get()
        )
    }

    single {
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
}
