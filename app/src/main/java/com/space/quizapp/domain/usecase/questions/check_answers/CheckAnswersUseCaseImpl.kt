package com.space.quizapp.domain.usecase.questions.check_answers

import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel

class CheckAnswersUseCaseImpl : CheckAnswersUseCase() {
    override suspend fun invoke(params: Nothing?): List<QuizQuestionDomainModel.QuizAnswerDomainModel> {
        // TODO( " IMPLEMENT CHECKING ANSWERS FUNCTIONALITY " )
    }
}
