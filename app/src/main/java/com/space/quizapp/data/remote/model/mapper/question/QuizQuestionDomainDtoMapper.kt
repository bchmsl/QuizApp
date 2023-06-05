package com.space.quizapp.data.remote.model.mapper.question

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.data.remote.model.QuizQuestionDto
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel

class QuizQuestionDomainDtoMapper : QuizModelMapper<QuizQuestionDomainModel, QuizQuestionDto> {
    override fun invoke(model: QuizQuestionDomainModel): QuizQuestionDto {
        return QuizQuestionDto(
            questionTitle = model.questionTitle,
            answers = model.answers,
            correctAnswer = model.correctAnswer,
            questionIndex = model.questionIndex
        )
    }
}
