package com.space.quizapp.presentation.di

import com.space.quizapp.presentation.ui.ui_home.vm.QuizHomeViewModel
import com.space.quizapp.presentation.ui.ui_points.vm.QuizPointsViewModel
import com.space.quizapp.presentation.ui.ui_question.vm.QuizQuestionViewModel
import com.space.quizapp.presentation.ui.ui_start.vm.QuizStartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        QuizStartViewModel(
            saveUserDataUC = get(),
            readUserTokenUC = get(),
            validateUserUC = get(),
            userMapper = get()
        )
    }
    viewModel {
        QuizHomeViewModel(
            readUserDataUC = get(),
            saveUserTokenUC = get(),
            retrieveSubjectsUC = get(),
            userMapper = get(),
            subjectMapper = get()
        )
    }
    viewModel {
        QuizQuestionViewModel(
            getNextQuestionUC = get(),
            checkAnswersUC = get(),
            saveUserPointsUC = get(),
            questionsCountUC = get(),
            updateGpaUC = get(),
            finishAlertUC = get(),
            questionMapper = get(),
            answerMapper = get()
        )
    }
    viewModel {
        QuizPointsViewModel(
            readUserSubjectsUC = get(),
            userSubjectsMapper = get(),
            saveUserTokenUC = get()
        )
    }
}
