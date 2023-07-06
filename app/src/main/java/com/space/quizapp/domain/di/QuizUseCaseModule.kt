package com.space.quizapp.domain.di

import com.space.quiz_impl.domain.usecase.FinishAlertUseCase
import com.space.quiz_impl.domain.usecase.GetQuestionsCountUseCase
import com.space.quiz_impl.domain.usecase.QuizCheckAnswersUseCase
import com.space.quiz_impl.domain.usecase.QuizGetNextQuestionUseCase
import com.space.quizapp.domain.usecase.questions.QuizSaveUserPointsUseCase
import com.space.quizapp.domain.usecase.questions.QuizUpdateGpaUseCase
import com.space.quizapp.domain.usecase.quiz.QuizRetrieveSubjectsUseCase
import com.space.quizapp.domain.usecase.user.QuizReadUserDataUseCase
import com.space.quizapp.domain.usecase.user.QuizReadUserTokenUseCase
import com.space.quizapp.domain.usecase.user.QuizSaveUserDataUseCase
import com.space.quizapp.domain.usecase.user.QuizSaveUserTokenUseCase
import com.space.quizapp.domain.usecase.user.ValidateUserUseCase
import com.space.quizapp.domain.usecase.user.subject.QuizReadUserSubjectsUseCase
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


    single {
        QuizCheckAnswersUseCase(
            quizRepository = get(),
        )
    }

    single {
        QuizGetNextQuestionUseCase(
            quizRepository = get()
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

    single {
        GetQuestionsCountUseCase(
            quizRepository = get()
        )
    }
    single {
        ValidateUserUseCase()
    }
    single {
        FinishAlertUseCase()
    }
}
