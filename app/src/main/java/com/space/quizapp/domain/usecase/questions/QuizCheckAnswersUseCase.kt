package com.space.quizapp.domain.usecase.questions

import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.domain.repository.quiz.QuizQuestionsRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import com.space.quizapp.presentation.ui.ui_question.util.QuizAnswerSelectedState

class QuizCheckAnswersUseCase(
    private val questionsRepository: QuizQuestionsRepository
) : QuizBaseUseCase<QuizCheckAnswersUseCase.CheckAnswerParams, QuizCheckAnswersUseCase.CheckAnswersResponse>() {

    override suspend fun invoke(params: CheckAnswerParams?): CheckAnswersResponse {
        val question = questionsRepository.getNextQuestion(params!!.subjectTitle)
        questionsRepository.updateQuestion(question!!.copy(isAnswered = true))
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


