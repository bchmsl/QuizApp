package com.space.common_koin

import com.space.main.presentation.ui.ui_home.vm.QuizHomeViewModel
import com.space.main.presentation.ui.ui_points.vm.QuizPointsViewModel
import com.space.main.presentation.ui.ui_start.vm.QuizStartViewModel
import com.space.quiz_impl.presentation.quiz.viewmodel.QuizQuestionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        QuizStartViewModel(
            saveUserDataUC = get(),
            readUserTokenUC = get(),
            validateUserUC = get(),
            userMapper = get(),
            appNavigator = get()
        )
    }
    viewModel {
        QuizHomeViewModel(
            readUserDataUC = get(),
            saveUserTokenUC = get(),
            retrieveSubjectsUC = get(),
            userMapper = get(),
            subjectMapper = get(),
            appNavigator = get(),
            quizNavigator = get()
        )
    }
    viewModel {
        QuizPointsViewModel(
            readUserSubjectsUC = get(),
            userSubjectsMapper = get(),
            saveUserTokenUC = get(),
            appNavigator = get(),
            quizNavigator = get()
        )
    }
    viewModel {
        QuizQuestionViewModel(
            updateGpaUC = get(),
            saveUserPointsUC = get(),
            getNextQuestionUC = get(),
            checkAnswersUC = get(),
            questionsCountUC = get(),
            finishAlertUC = get(),
            questionMapper = get(),
            answerMapper = get(),
            appNavigator = get()
        )
    }
}
