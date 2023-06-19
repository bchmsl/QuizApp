package com.space.quizapp.domain.di

import com.space.quizapp.domain.usecase.questions.AddPointsToSubjectUseCase
import com.space.quizapp.domain.usecase.questions.QuizCheckAnswersUseCase
import com.space.quizapp.domain.usecase.questions.QuizGetPointsUseCase
import com.space.quizapp.domain.usecase.questions.QuizSaveUserPointsUseCase
import com.space.quizapp.domain.usecase.questions.next_question.QuizGetNextQuestionUseCase
import com.space.quizapp.domain.usecase.quiz.QuizRetrieveSubjectsUseCase
import com.space.quizapp.domain.usecase.user.QuizReadUserTokenUseCase
import com.space.quizapp.domain.usecase.user.QuizSaveUserDataUseCase
import com.space.quizapp.domain.usecase.user.QuizSaveUserTokenUseCase
import com.space.quizapp.domain.usecase.user.QuizUpdateGpaUseCase
import com.space.quizapp.domain.usecase.user.read_user_data.QuizReadUserDataUseCase
import com.space.quizapp.domain.usecase.user.subject.QuizReadUserSubjectsUseCase
import com.space.quizapp.domain.usecase.user.subject.QuizSaveUserSubjectUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single {
        QuizSaveUserDataUseCase(
            saveUserToken = get(),
            repository = get()
        )
    }

    single {
        QuizReadUserDataUseCase(
            readUserToken = get(),
            repository = get()
        )
    }

    single {
        QuizSaveUserTokenUseCase(
            repository = get()
        )
    }

    single {
        QuizReadUserTokenUseCase(
            repository = get()
        )
    }


    single {
        QuizCheckAnswersUseCase(
            questionsRepository = get(),
            addPointsToSubjectUseCase = get()
        )
    }

    single {
        QuizGetNextQuestionUseCase(
            questionsRepository = get()
        )
    }

    single {
        QuizRetrieveSubjectsUseCase(
            repository = get()
        )
    }

    single {
        QuizGetPointsUseCase(
            userSubjectRepository = get()
        )
    }
    single {
        AddPointsToSubjectUseCase(
            userSubjectRepository = get()
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
        QuizSaveUserSubjectUseCase(
            repository = get(),
            readUserData = get()
        )
    }

    single {
        QuizUpdateGpaUseCase(
            readUserTokenUseCase = get(),
            readUserSubjectsUseCase = get(),
            subjectsRepository = get(),
            userDataRepository = get()
        )
    }

    single {
        QuizSaveUserPointsUseCase(
            readUserDataUseCase = get(),
            userSubjectRepository = get()
        )
    }
}
