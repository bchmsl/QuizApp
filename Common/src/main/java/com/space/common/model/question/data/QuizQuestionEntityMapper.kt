package com.space.common.model.question.data

import com.space.common.mapper.QuizEntityMapper
import com.space.common.model.question.domain.QuizQuestionDomainModel

class QuizQuestionEntityMapper :
    QuizEntityMapper<QuizQuestionEntity, QuizQuestionDomainModel> {
    override fun toDomain(model: QuizQuestionEntity): QuizQuestionDomainModel {
        return with(model) {
            QuizQuestionDomainModel(
                questionTitle = questionTitle,
                answers = answers.map {
                    QuizQuestionDomainModel
                        .QuizAnswerDomainModel(
                            it,
                            correctAnswer == it
                        )
                }.toMutableList(),
                correctAnswer = QuizQuestionDomainModel.QuizAnswerDomainModel(
                    correctAnswer,
                    true
                ),
                subjectId = subjectId,
                questionIndex = questionIndex,
                isAnswered = isAnswered,
                isLastQuestion = isLastQuestion,
                subjectTitle = subjectTitle,
                points = points
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
                questionIndex = questionIndex,
                isAnswered = isAnswered,
                isLastQuestion = isLastQuestion,
                subjectTitle = subjectTitle,
                points = points
            )
        }
    }
}
