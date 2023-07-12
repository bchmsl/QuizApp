package com.space.common.model.question.data

import com.space.common.mapper.EntityMapper
import com.space.common.model.question.domain.QuestionDomainModel

class QuestionEntityMapper :
    EntityMapper<QuestionEntity, QuestionDomainModel> {
    override fun toDomain(model: QuestionEntity): QuestionDomainModel {
        return with(model) {
            QuestionDomainModel(
                questionTitle = questionTitle,
                answers = answers.map {
                    QuestionDomainModel
                        .AnswerDomainModel(
                            it,
                            correctAnswer == it
                        )
                }.toMutableList(),
                correctAnswer = QuestionDomainModel.AnswerDomainModel(
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

    override fun toEntity(model: QuestionDomainModel): QuestionEntity {
        return with(model) {
            QuestionEntity(
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
