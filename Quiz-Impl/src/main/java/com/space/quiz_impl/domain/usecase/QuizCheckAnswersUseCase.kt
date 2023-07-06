package com.space.quiz_impl.domain.usecase

import com.space.common.base.usecase.QuizBaseUseCase
import com.space.quiz_impl.domain.model.QuizQuestionDomainModel
import com.space.quiz_impl.domain.repository.QuizRepository
import com.space.quiz_impl.presentation.quiz.model.QuizAnswerSelectedState

class QuizCheckAnswersUseCase(
    private val quizRepository: QuizRepository
) : QuizBaseUseCase<QuizCheckAnswersUseCase.CheckAnswerParams, QuizCheckAnswersUseCase.CheckAnswersResponse>() {

    override suspend fun invoke(params: CheckAnswerParams?): CheckAnswersResponse {
        val question = quizRepository.getNextQuestion(params!!.subjectTitle)
        quizRepository.updateQuestion(question!!.copy(isAnswered = true))
        val checkedAnswersList = params.answersList
        if (params.answerModel.isCorrect) {
            checkedAnswersList.find { it.isCorrect }?.answerSelectedState =
                QuizAnswerSelectedState.ANSWER_SELECTED_CORRECT
        } else {
            checkedAnswersList.find { it.isCorrect }?.answerSelectedState =
                QuizAnswerSelectedState.ANSWER_SELECTED_POSITIVE
            checkedAnswersList.find { it == params.answerModel }?.answerSelectedState =
                QuizAnswerSelectedState.ANSWER_SELECTED_NEGATIVE
        }
        return CheckAnswersResponse(params.answerModel.isCorrect, checkedAnswersList)
    }

    data class CheckAnswerParams(
        val answerModel: QuizQuestionDomainModel.QuizAnswerDomainModel,
        val answersList: List<QuizQuestionDomainModel.QuizAnswerDomainModel>,
        val subjectTitle: String
    )

    data class CheckAnswersResponse(
        val isCorrect: Boolean,
        val answersList: List<QuizQuestionDomainModel.QuizAnswerDomainModel>
    )
}


