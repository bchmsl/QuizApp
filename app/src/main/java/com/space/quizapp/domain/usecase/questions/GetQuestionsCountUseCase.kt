package com.space.quizapp.domain.usecase.questions

import com.space.quizapp.domain.repository.quiz.QuizQuestionsRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase

class GetQuestionsCountUseCase(
    private val questionsRepository: QuizQuestionsRepository
) : QuizBaseUseCase<String, Int>() {
    override suspend fun invoke(params: String?): Int {
        return questionsRepository.getQuestionsCount(params!!)
    }
}