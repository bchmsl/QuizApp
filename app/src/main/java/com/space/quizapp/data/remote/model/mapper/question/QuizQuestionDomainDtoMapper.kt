package com.space.quizapp.data.remote.model.mapper.question

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.data.remote.model.QuizSubjectDto
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel

class QuizQuestionDomainDtoMapper :
    QuizModelMapper<QuizQuestionDomainModel, QuizSubjectDto.QuizQuestionDto> {
    override fun invoke(model: QuizQuestionDomainModel): QuizSubjectDto.QuizQuestionDto {
        return QuizSubjectDto.QuizQuestionDto(
            questionTitle = model.questionTitle,
            answers = model.answers.map { it.answerOption },
            correctAnswer = model.correctAnswer.answerOption,
            subjectId = model.subjectId,
            questionIndex = model.questionIndex
        )
    }
}
