package com.space.quizapp.presentation.di

import com.space.quizapp.domain.di.QuizUseCaseNames
import com.space.quizapp.presentation.ui.ui_home.vm.QuizHomeViewModel
import com.space.quizapp.presentation.ui.ui_points.vm.QuizPointsViewModel
import com.space.quizapp.presentation.ui.ui_question.vm.QuizQuestionViewModel
import com.space.quizapp.presentation.ui.ui_start.vm.QuizStartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        QuizStartViewModel(
            saveUserDataUC = get(QuizUseCaseNames.SAVE_USER_DATA),
            readUserTokenUC = get(QuizUseCaseNames.READ_USER_TOKEN),
            userMapper = get()
        )
    }
    viewModel {
        QuizHomeViewModel(
            readUserDataUC = get(QuizUseCaseNames.READ_USER_DATA),
            saveUserTokenUC = get(QuizUseCaseNames.SAVE_USER_TOKEN),
            retrieveSubjectsUC = get(QuizUseCaseNames.RETRIEVE_SUBJECTS),
            userMapper = get(),
            subjectMapper = get()
        )
    }
    viewModel {
        QuizQuestionViewModel(
            getNextQuestionUC = get(QuizUseCaseNames.GET_NEXT_QUESTION),
            checkAnswersUC = get(QuizUseCaseNames.CHECK_ANSWERS),
            retrieveQuestionsUC = get(QuizUseCaseNames.RETRIEVE_QUESTIONS),
            getPointsUC = get(QuizUseCaseNames.GET_POINTS),
            questionMapper = get(),
            answerMapper = get()
        )
    }
    viewModel {
        QuizPointsViewModel()
    }
}
