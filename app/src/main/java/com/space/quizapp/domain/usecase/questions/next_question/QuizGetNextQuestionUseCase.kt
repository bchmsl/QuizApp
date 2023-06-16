package com.space.quizapp.domain.usecase.questions.next_question

import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import com.space.quizapp.presentation.ui.ui_question.manager.QuizManager

class QuizGetNextQuestionUseCase(
    private val manager: QuizManager,
) : QuizBaseUseCase<Unit, QuizGetNextQuestionResponse<QuizQuestionDomainModel>>() {
    override suspend fun invoke(params: Unit?): QuizGetNextQuestionResponse<QuizQuestionDomainModel> {
        val nextQuestion = manager.getNextQuestion()
        val isLastQuestion = manager.isLastQuestion()
        return QuizGetNextQuestionResponse(nextQuestion, isLastQuestion)
    }
}
