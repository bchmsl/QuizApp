package com.space.main_impl.data.remote.model.mapper

import com.space.common.mapper.QuizDtoMapper
import com.space.common.model.question.domain.QuizQuestionDomainModel
import com.space.common.util.QuizConstants.QUESTION_SCORE
import com.space.main_impl.data.remote.model.QuizSubjectDto

class QuizQuestionDtoMapper :
    QuizDtoMapper<QuizSubjectDto.QuizQuestionDto, QuizQuestionDomainModel> {
    var isLastQuestion: ((QuizSubjectDto.QuizQuestionDto) -> Boolean)? = null
    var subjectTitle: ((QuizSubjectDto.QuizQuestionDto) -> String)? = null
    override fun toDomain(model: QuizSubjectDto.QuizQuestionDto): com.space.common.model.question.domain.QuizQuestionDomainModel {
        return with(model) {
            com.space.common.model.question.domain.QuizQuestionDomainModel(
                questionTitle = questionTitle,
                answers = answers.map {
                    com.space.common.model.question.domain.QuizQuestionDomainModel.QuizAnswerDomainModel(
                        it,
                        correctAnswer == it
                    )
                }
                    .toMutableList(),
                correctAnswer = com.space.common.model.question.domain.QuizQuestionDomainModel.QuizAnswerDomainModel(
                    correctAnswer,
                    true
                ),
                subjectId = subjectId,
                questionIndex = questionIndex,
                isLastQuestion = isLastQuestion?.invoke(this) ?: false,
                isAnswered = false,
                subjectTitle = subjectTitle?.invoke(this) ?: "",
                points = QUESTION_SCORE
            )
        }
    }
}
