package com.space.quizapp.domain.usecase.questions.check_answers

import com.space.quizapp.presentation.ui.ui_question.model.AnswerModel

interface CheckAnswersUseCase {
    suspend operator fun invoke(): List<AnswerModel>
}
