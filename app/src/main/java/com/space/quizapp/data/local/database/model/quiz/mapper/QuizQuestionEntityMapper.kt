package com.space.quizapp.data.local.database.model.quiz.mapper

import com.space.quizapp.common.mapper.QuizEntityMapper
import com.space.quizapp.data.local.database.model.quiz.QuizQuestionEntity
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.presentation.ui.ui_question.util.QuizAnswerSelectedState

class QuizQuestionEntityMapper : QuizEntityMapper<QuizQuestionEntity, QuizQuestionDomainModel> {
    override fun toDomain(model: QuizQuestionEntity): QuizQuestionDomainModel {
        return with(model) {
            QuizQuestionDomainModel(
                questionTitle = questionTitle,
                answers = answers.map {
                    QuizQuestionDomainModel
                        .QuizAnswerDomainModel(
                            it,
                            QuizAnswerSelectedState.ANSWER_UNSELECTED
                        )
                }.toMutableList(),
                correctAnswer = QuizQuestionDomainModel
                    .QuizAnswerDomainModel(
                        correctAnswer,
                        QuizAnswerSelectedState.ANSWER_SELECTED_CORRECT
                    ),
                subjectId = subjectId,
                questionIndex = questionIndex
            )
        }
    }

    override fun toEntity(model: QuizQuestionDomainModel): QuizQuestionEntity {
        return with(model) {
            QuizQuestionEntity(
                questionTitle = questionTitle,
                answers = answers.map { it.answerOption },
                correctAnswer = correctAnswer.answerOption,
                subjectId = subjectId,
                questionIndex = questionIndex
            )
        }
    }
}
