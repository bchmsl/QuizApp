package com.space.quizapp.data.local.database.model.quiz.mapper.question

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.data.local.database.model.quiz.QuizQuestionEntity
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.presentation.ui.ui_question.util.QuizAnswerSelectedState

class QuizQuestionEntityDomainMapper :
    QuizModelMapper<QuizQuestionEntity, QuizQuestionDomainModel> {
    override fun invoke(model: QuizQuestionEntity): QuizQuestionDomainModel {
        return QuizQuestionDomainModel(
            questionTitle = model.questionTitle,
            answers = model.answers.map {
                QuizQuestionDomainModel.QuizAnswerDomainModel(
                    it,
                    QuizAnswerSelectedState.ANSWER_UNSELECTED
                )
            }.toMutableList(),
            correctAnswer = QuizQuestionDomainModel.QuizAnswerDomainModel(
                model.correctAnswer,
                QuizAnswerSelectedState.ANSWER_SELECTED_CORRECT
            ),
            subjectId = model.subjectId,
            questionIndex = model.questionIndex
        )
    }
}
