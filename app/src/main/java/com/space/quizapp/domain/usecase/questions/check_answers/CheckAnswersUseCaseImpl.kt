package com.space.quizapp.domain.usecase.questions.check_answers

import com.space.quizapp.presentation.ui.ui_question.manager.QuestionManager
import com.space.quizapp.presentation.ui.ui_question.model.AnswerModel

class CheckAnswersUseCaseImpl(
    private val questionManager: QuestionManager
) : CheckAnswersUseCase {

    override suspend fun invoke(): List<AnswerModel> {
        TODO("Not yet implemented")
    }
}
