package com.space.quizapp.domain.usecase.questions.check_answers

import com.space.quizapp.presentation.model.quiz.QuizAnswerUiModel
import com.space.quizapp.presentation.ui.ui_question.manager.QuestionManager

class CheckAnswersUseCaseImpl(
    private val questionManager: QuestionManager
) : CheckAnswersUseCase {

    override suspend fun invoke(): List<QuizAnswerUiModel> {
        TODO("Not yet implemented")
    }
}
