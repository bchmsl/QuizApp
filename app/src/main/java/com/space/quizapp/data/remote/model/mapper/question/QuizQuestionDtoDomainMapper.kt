package com.space.quizapp.data.remote.model.mapper.question

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.data.remote.model.QuizQuestionDto
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel

class QuizQuestionDtoDomainMapper : QuizModelMapper<QuizQuestionDto, QuizQuestionDomainModel> {
    override fun invoke(model: QuizQuestionDto): QuizQuestionDomainModel {
        return QuizQuestionDomainModel(
            questionTitle = model.questionTitle,
            answers = model.answers,
            correctAnswer = model.correctAnswer,
            questionIndex = model.questionIndex
        )
    }
}
