package com.space.common_koin

import com.space.main.presentation.ui.ui_home.vm.HomeViewModel
import com.space.main.presentation.ui.ui_points.vm.PointsViewModel
import com.space.main.presentation.ui.ui_start.vm.StartViewModel
import com.space.quiz_impl.presentation.quiz.viewmodel.QuestionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        StartViewModel(
            saveUserDataUC = get(),
            readUserTokenUC = get(),
            validateUserUC = get(),
            userMapper = get(),
            appNavigator = get()
        )
    }
    viewModel {
        HomeViewModel(
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
        PointsViewModel(
            readUserSubjectsUC = get(),
            userSubjectsMapper = get(),
            saveUserTokenUC = get(),
            appNavigator = get(),
            quizNavigator = get()
        )
    }
    viewModel {
        QuestionViewModel(
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
