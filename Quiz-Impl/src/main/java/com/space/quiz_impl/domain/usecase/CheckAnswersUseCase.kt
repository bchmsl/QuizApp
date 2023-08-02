package com.space.quiz_impl.domain.usecase

import com.space.common.base.usecase.BaseUseCase
import com.space.common.model.question.domain.QuestionDomainModel
import com.space.common.model.question.model.AnswerSelectedState
import com.space.quiz_api.GetNextQuestion
import com.space.quiz_api.UpdateQuestion

class CheckAnswersUseCase(
    private val getNextQuestion: GetNextQuestion,
    private val updateQuestion: UpdateQuestion
) : BaseUseCase<CheckAnswersUseCase.CheckAnswerParams, CheckAnswersUseCase.CheckAnswersResponse>() {

    override suspend fun invoke(params: CheckAnswerParams?): CheckAnswersResponse {
        val question = getNextQuestion(params!!.subjectTitle)
        updateQuestion(question!!.copy(isAnswered = true))
        val checkedAnswersList = params.answersList
        if (params.answerModel.isCorrect) {
            checkedAnswersList.find { it.isCorrect }?.answerSelectedState =
                AnswerSelectedState.ANSWER_SELECTED_CORRECT
        } else {
            checkedAnswersList.find { it.isCorrect }?.answerSelectedState =
                AnswerSelectedState.ANSWER_SELECTED_POSITIVE
            checkedAnswersList.find { it == params.answerModel }?.answerSelectedState =
                AnswerSelectedState.ANSWER_SELECTED_NEGATIVE
        }
        return CheckAnswersResponse(params.answerModel.isCorrect, checkedAnswersList)
    }

    data class CheckAnswerParams(
        val answerModel: QuestionDomainModel.AnswerDomainModel,
        val answersList: List<QuestionDomainModel.AnswerDomainModel>,
        val subjectTitle: String
    )

    data class CheckAnswersResponse(
        val isCorrect: Boolean,
        val answersList: List<QuestionDomainModel.AnswerDomainModel>
    )
}


