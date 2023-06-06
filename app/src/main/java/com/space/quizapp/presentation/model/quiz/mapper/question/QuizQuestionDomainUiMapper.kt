package com.space.quizapp.presentation.model.quiz.mapper.question

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.presentation.model.quiz.QuizQuestionUiModel
import com.space.quizapp.presentation.model.quiz.mapper.answer.QuizAnswerDomainUiMapper

class QuizQuestionDomainUiMapper(
    private val quizAnswerDomainUiMapper: QuizAnswerDomainUiMapper
) : QuizModelMapper<QuizQuestionDomainModel, QuizQuestionUiModel> {
    override fun invoke(model: QuizQuestionDomainModel): QuizQuestionUiModel {
        return QuizQuestionUiModel(
            questionTitle = model.questionTitle,
            answers = model.answers.map { quizAnswerDomainUiMapper(it) },
            correctAnswer = quizAnswerDomainUiMapper(model.correctAnswer),
            subjectId = model.subjectId,
            questionIndex = model.questionIndex
        )
    }
}
