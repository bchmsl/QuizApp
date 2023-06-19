package com.space.quizapp.data.remote.model.mapper

import com.space.quizapp.common.mapper.QuizDtoMapper
import com.space.quizapp.data.remote.model.QuizSubjectDto
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel

class QuizQuestionDtoMapper :
    QuizDtoMapper<QuizSubjectDto.QuizQuestionDto, QuizQuestionDomainModel> {
    var isLastQuestion: ((QuizSubjectDto.QuizQuestionDto) -> Boolean)? = null
    var subjectTitle: ((QuizSubjectDto.QuizQuestionDto) -> String)? = null
    override fun toDomain(model: QuizSubjectDto.QuizQuestionDto): QuizQuestionDomainModel {
        return with(model) {
            QuizQuestionDomainModel(
                questionTitle = questionTitle,
                answers = answers.map {
                    QuizQuestionDomainModel.QuizAnswerDomainModel(
                        it,
                        correctAnswer == it
                    )
                }
                    .toMutableList(),
                correctAnswer = QuizQuestionDomainModel.QuizAnswerDomainModel(correctAnswer, true),
                subjectId = subjectId,
                questionIndex = questionIndex,
                isLastQuestion = isLastQuestion?.invoke(this) ?: false,
                isAnswered = false,
                subjectTitle = subjectTitle?.invoke(this) ?: ""
            )
        }
    }
}
