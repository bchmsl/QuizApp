package com.space.quizapp.domain.di

import com.space.quizapp.domain.usecase.questions.FinishAlertUseCase
import com.space.quizapp.domain.usecase.questions.GetQuestionsCountUseCase
import com.space.quizapp.domain.usecase.questions.QuizCheckAnswersUseCase
import com.space.quizapp.domain.usecase.questions.QuizSaveUserPointsUseCase
import com.space.quizapp.domain.usecase.questions.next_question.QuizGetNextQuestionUseCase
import com.space.quizapp.domain.usecase.quiz.QuizRetrieveSubjectsUseCase
import com.space.quizapp.domain.usecase.user.QuizReadUserDataUseCase
import com.space.quizapp.domain.usecase.user.QuizReadUserTokenUseCase
import com.space.quizapp.domain.usecase.user.QuizSaveUserDataUseCase
import com.space.quizapp.domain.usecase.user.QuizSaveUserTokenUseCase
import com.space.quizapp.domain.usecase.user.QuizUpdateGpaUseCase
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
            questionsRepository = get(),
        )
    }

    single {
        QuizGetNextQuestionUseCase(
            questionsRepository = get()
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
            questionsRepository = get()
        )
    }
    single {
        ValidateUserUseCase()
    }
    single {
        FinishAlertUseCase()
    }
}
