package com.space.quizapp.domain.usecase.questions

import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import com.space.quizapp.presentation.ui.ui_question.manager.QuizManager

class QuizGetPointsUseCase(
    private val manager: QuizManager
) : QuizBaseUseCase<Unit, Int>() {

    override suspend fun invoke(params: Unit?): Int {
        return manager.getUserPoints()
    }
}
