package com.space.quizapp.domain.usecase.questions.next_question

import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.domain.repository.quiz.QuizQuestionsRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase

class QuizGetNextQuestionUseCase(
    private val questionsRepository: QuizQuestionsRepository,
) : QuizBaseUseCase<String, QuizQuestionDomainModel?>() {
    override suspend fun invoke(params: String?): QuizQuestionDomainModel? {
        return questionsRepository.getNextQuestion(params!!)
    }
}
