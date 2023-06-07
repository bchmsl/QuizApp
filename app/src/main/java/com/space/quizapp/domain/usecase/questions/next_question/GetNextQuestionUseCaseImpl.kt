package com.space.quizapp.domain.usecase.questions.next_question

import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel

class GetNextQuestionUseCaseImpl : GetNextQuestionUseCase() {
    override suspend fun invoke(params: Unit?): QuizQuestionDomainModel {
        return questionManager.getNextQuestion()
    }
}
