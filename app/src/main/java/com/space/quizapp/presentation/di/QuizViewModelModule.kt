package com.space.quizapp.presentation.di

import com.space.quizapp.presentation.model.quiz.mapper.question.QuizQuestionDomainUiMapper
import com.space.quizapp.presentation.model.user.mapper.subject.QuizUserSubjectDomainUiMapper
import com.space.quizapp.presentation.model.user.mapper.subject.QuizUserSubjectUiDomainMapper
import com.space.quizapp.presentation.model.user.mapper.user.QuizUserDomainUiMapper
import com.space.quizapp.presentation.model.user.mapper.user.QuizUserUiDomainMapper
import com.space.quizapp.presentation.ui.ui_home.vm.QuizHomeViewModel
import com.space.quizapp.presentation.ui.ui_question.manager.QuestionManagerImpl
import com.space.quizapp.presentation.ui.ui_question.vm.QuizQuestionViewModel
import com.space.quizapp.presentation.ui.ui_start.vm.QuizStartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        QuizStartViewModel(
            get(),
            get(),
            QuizUserUiDomainMapper(
                QuizUserSubjectUiDomainMapper()
            )
        )
    }
    viewModel {
        QuizHomeViewModel(
            get(),
            get(),
            QuizUserDomainUiMapper(
                QuizUserSubjectDomainUiMapper()
            ),
            QuizUserSubjectDomainUiMapper(),
            get()
        )
    }
    viewModel {
        QuizQuestionViewModel(
            get(),
            QuizQuestionsDomainUiMapper(
                QuizQuestionDomainUiMapper()
            ),
            QuestionManagerImpl()
        )
    }
}
