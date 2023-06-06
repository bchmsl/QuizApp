package com.space.quizapp.presentation.model.quiz.mapper.question

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.presentation.model.quiz.QuizQuestionUiModel
import com.space.quizapp.presentation.model.quiz.mapper.answer.QuizAnswerUiDomainMapper

class QuizQuestionUiDomainMapper(
    private val quizAnswerUiDomainMapper: QuizAnswerUiDomainMapper
) : QuizModelMapper<QuizQuestionUiModel, QuizQuestionDomainModel> {
    override fun invoke(model: QuizQuestionUiModel): QuizQuestionDomainModel {
        return QuizQuestionDomainModel(
            questionTitle = model.questionTitle,
            answers = model.answers.map { quizAnswerUiDomainMapper(it) },
            correctAnswer = quizAnswerUiDomainMapper(model.correctAnswer),
            subjectId = model.subjectId,
            questionIndex = model.questionIndex
        )
    }
}
