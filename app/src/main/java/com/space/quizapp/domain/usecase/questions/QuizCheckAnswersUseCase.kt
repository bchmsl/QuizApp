package com.space.quizapp.domain.usecase.questions

import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import com.space.quizapp.presentation.ui.ui_question.manager.QuizManager

class QuizCheckAnswersUseCase(
    private val manager: QuizManager
) : QuizBaseUseCase<QuizQuestionDomainModel.QuizAnswerDomainModel, List<QuizQuestionDomainModel.QuizAnswerDomainModel>>() {

    override suspend fun invoke(answer: QuizQuestionDomainModel.QuizAnswerDomainModel?): List<QuizQuestionDomainModel.QuizAnswerDomainModel> {
        return manager.checkAnswer(answer!!)
    }
}
