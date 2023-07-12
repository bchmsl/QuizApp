package com.space.common.model.question.model

import com.space.common.mapper.UiMapper
import com.space.common.model.question.domain.QuestionDomainModel

class QuestionUiMapper(
    private val answerUiMapper: AnswerUiMapper
) : UiMapper<QuestionUiModel, QuestionDomainModel> {
    override fun toDomain(model: QuestionUiModel): QuestionDomainModel {
        return with(model) {
            QuestionDomainModel(
                questionTitle = questionTitle,
                answers = answers.map { answerUiMapper.toDomain(it) }.toMutableList(),
                correctAnswer = answerUiMapper.toDomain(correctAnswer),
                subjectId = subjectId,
                questionIndex = questionIndex,
                isLastQuestion = isLastQuestion,
                isAnswered = isAnswered,
                subjectTitle = subjectTitle,
                points = points
            )
        }
    }

    override fun toUi(model: QuestionDomainModel): QuestionUiModel {
        return with(model) {
            QuestionUiModel(
                questionTitle = questionTitle,
                answers = answers.map { answerUiMapper.toUi(it) },
                correctAnswer = answerUiMapper.toUi(correctAnswer),
                subjectId = subjectId,
                questionIndex = questionIndex,
                isLastQuestion = isLastQuestion,
                isAnswered = isAnswered,
                subjectTitle = subjectTitle,
                points = points
            )
        }
    }
}
