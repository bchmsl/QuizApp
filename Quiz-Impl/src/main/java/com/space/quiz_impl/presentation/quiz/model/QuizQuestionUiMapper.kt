package com.space.quiz_impl.presentation.quiz.model

import com.space.quiz_impl.presentation.quiz.model.QuizQuestionUiModel
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel

class QuizQuestionUiMapper(
    private val answerUiMapper: QuizAnswerUiMapper
) : com.space.common.mapper.QuizUiMapper<QuizQuestionUiModel, QuizQuestionDomainModel> {
    override fun toDomain(model: QuizQuestionUiModel): QuizQuestionDomainModel {
        return with(model) {
            QuizQuestionDomainModel(
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

    override fun toUi(model: QuizQuestionDomainModel): QuizQuestionUiModel {
        return with(model) {
            com.space.common.model.question.presentation.QuizQuestionUiModel(
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
