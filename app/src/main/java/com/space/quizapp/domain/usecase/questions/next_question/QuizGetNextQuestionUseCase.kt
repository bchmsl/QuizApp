package com.space.quizapp.domain.usecase.questions.next_question

import com.space.quizapp.data.repository.quiz.QuizQuestionsRepositoryImpl
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase

class QuizGetNextQuestionUseCase(
    private val questionsRepository: QuizQuestionsRepositoryImpl,
) : QuizBaseUseCase<String, QuizQuestionDomainModel?>() {
    override suspend fun invoke(params: String?): QuizQuestionDomainModel? {
        return questionsRepository.getNextQuestion(params!!)
    }
}
