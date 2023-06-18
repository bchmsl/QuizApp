package com.space.quizapp.data.remote.model.mapper

import com.space.quizapp.common.mapper.QuizDtoMapper
import com.space.quizapp.data.remote.model.QuizSubjectDto
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel

class QuizQuestionDtoMapper :
    QuizDtoMapper<QuizSubjectDto.QuizQuestionDto, QuizQuestionDomainModel> {
    override fun toDomain(model: QuizSubjectDto.QuizQuestionDto): QuizQuestionDomainModel {
        return with(model) {
            QuizQuestionDomainModel(
                questionTitle = questionTitle,
                answers = answers.map { QuizQuestionDomainModel.QuizAnswerDomainModel(it) }
                    .toMutableList(),
                correctAnswer = QuizQuestionDomainModel.QuizAnswerDomainModel(correctAnswer),
                subjectId = subjectId,
                questionIndex = questionIndex
            )
        }
    }
}
