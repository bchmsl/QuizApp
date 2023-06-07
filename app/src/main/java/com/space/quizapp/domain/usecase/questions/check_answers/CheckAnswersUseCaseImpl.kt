package com.space.quizapp.domain.usecase.questions.check_answers

import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel

class CheckAnswersUseCaseImpl :
    CheckAnswersUseCase() {
    override suspend fun invoke(answer: QuizQuestionDomainModel.QuizAnswerDomainModel?): List<QuizQuestionDomainModel.QuizAnswerDomainModel> {
        return quizManager.checkAnswer(answer!!)
    }
}
