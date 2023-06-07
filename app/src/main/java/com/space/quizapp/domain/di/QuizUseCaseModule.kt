package com.space.quizapp.domain.di

import com.space.quizapp.domain.usecase.questions.check_answers.CheckAnswersUseCase
import com.space.quizapp.domain.usecase.questions.check_answers.CheckAnswersUseCaseImpl
import com.space.quizapp.domain.usecase.questions.next_question.GetNextQuestionUseCase
import com.space.quizapp.domain.usecase.questions.next_question.GetNextQuestionUseCaseImpl
import com.space.quizapp.domain.usecase.quiz.retrieve_questions.QuizRetrieveQuestionsUseCase
import com.space.quizapp.domain.usecase.quiz.retrieve_questions.QuizRetrieveQuestionsUseCaseImpl
import com.space.quizapp.domain.usecase.quiz.retrieve_subjects.QuizRetrieveSubjectsUseCase
import com.space.quizapp.domain.usecase.quiz.retrieve_subjects.QuizRetrieveSubjectsUseCaseImpl
import com.space.quizapp.domain.usecase.user.read_user_data.QuizRetrieveUserDataUseCase
import com.space.quizapp.domain.usecase.user.read_user_data.RetrieveUserDataUseCaseImpl
import com.space.quizapp.domain.usecase.user.read_user_token.QuizReadUserTokenUseCase
import com.space.quizapp.domain.usecase.user.read_user_token.QuizReadUserTokenUseCaseImpl
import com.space.quizapp.domain.usecase.user.save_user_data.QuizSaveUserDataUseCase
import com.space.quizapp.domain.usecase.user.save_user_data.QuizSaveUserDataUseCaseImpl
import com.space.quizapp.domain.usecase.user.save_user_token.QuizSaveUserTokenUseCase
import com.space.quizapp.domain.usecase.user.save_user_token.QuizSaveUserTokenUseCaseImpl
import com.space.quizapp.presentation.ui.ui_question.manager.QuizManager
import com.space.quizapp.presentation.ui.ui_question.manager.QuizManagerImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<QuizSaveUserDataUseCase> {
        QuizSaveUserDataUseCaseImpl(
            get()
        )
    }
    single<QuizRetrieveUserDataUseCase> { RetrieveUserDataUseCaseImpl(get()) }
    single<QuizSaveUserTokenUseCase> { QuizSaveUserTokenUseCaseImpl() }
    single<QuizReadUserTokenUseCase> { QuizReadUserTokenUseCaseImpl() }

    single<CheckAnswersUseCase> { CheckAnswersUseCaseImpl() }
    single<GetNextQuestionUseCase> { GetNextQuestionUseCaseImpl() }
    single<QuizRetrieveQuestionsUseCase> { QuizRetrieveQuestionsUseCaseImpl() }
    single<QuizRetrieveSubjectsUseCase> { QuizRetrieveSubjectsUseCaseImpl() }

    single<QuizManager> { QuizManagerImpl() }
}
