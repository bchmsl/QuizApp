package com.space.quizapp.domain.di

import com.space.quizapp.domain.usecase.questions.AddPointsToSubjectUseCase
import com.space.quizapp.domain.usecase.questions.GetQuestionsCountUseCase
import com.space.quizapp.domain.usecase.questions.QuizCheckAnswersUseCase
import com.space.quizapp.domain.usecase.questions.QuizGetPointsUseCase
import com.space.quizapp.domain.usecase.questions.QuizResetUserPointsUseCase
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
            addPointsToSubjectUC = get()
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
            userSubjectRepository = get(),
            readUserDataUC = get()
        )
    }

    single {
        QuizUpdateGpaUseCase(
            readUserSubjectsUC = get(),
            subjectsRepository = get(),
            userDataRepository = get(),
            readUserDataUC = get()
        )
    }

    single {
        QuizSaveUserPointsUseCase(
            readUserDataUC = get(),
            userSubjectRepository = get()
        )
    }

    single {
        QuizResetUserPointsUseCase(
            userSubjectRepository = get()
        )
    }

    single {
        GetQuestionsCountUseCase(
            questionsRepository = get()
        )
    }
}
