package com.space.quizapp.presentation.model.quiz.mapper

import com.space.quizapp.common.mapper.QuizUiMapper
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.presentation.model.quiz.QuizQuestionUiModel

class QuizQuestionUiMapper(
    private val answerUiMapper: QuizAnswerUiMapper
) : QuizUiMapper<QuizQuestionUiModel, QuizQuestionDomainModel> {
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
            QuizQuestionUiModel(
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
