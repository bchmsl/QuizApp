package com.space.quizapp.domain.usecase.questions

import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.domain.repository.quiz.QuizQuestionsRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase

class QuizCheckAnswersUseCase(
    private val questionsRepository: QuizQuestionsRepository
) : QuizBaseUseCase<CheckAnswerParams, Boolean>() {

    override suspend fun invoke(params: CheckAnswerParams?): Boolean {
        val question = questionsRepository.getNextQuestion(params!!.subjectTitle)
        questionsRepository.updateQuestion(question!!.copy(isAnswered = true))
        return params.answerModel.isCorrect
    }
}
data class CheckAnswerParams(
    val answerModel: QuizQuestionDomainModel.QuizAnswerDomainModel,
    val subjectTitle: String
)
