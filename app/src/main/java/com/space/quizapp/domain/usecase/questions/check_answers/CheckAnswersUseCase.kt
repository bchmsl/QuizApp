package com.space.quizapp.domain.usecase.questions.check_answers

import com.space.quizapp.presentation.model.quiz.QuizAnswerUiModel

interface CheckAnswersUseCase {
    suspend operator fun invoke(): List<QuizAnswerUiModel>
}
