package com.space.main.data.remote.model.mapper

import com.space.common.mapper.DtoMapper
import com.space.common.model.question.domain.QuestionDomainModel
import com.space.common.util.Constants.QUESTION_SCORE
import com.space.main.data.remote.model.SubjectDto

class QuestionDtoMapper :
    DtoMapper<SubjectDto.QuizQuestionDto, QuestionDomainModel> {
    var isLastQuestion: ((SubjectDto.QuizQuestionDto) -> Boolean)? = null
    var subjectTitle: ((SubjectDto.QuizQuestionDto) -> String)? = null
    override fun toDomain(model: SubjectDto.QuizQuestionDto): com.space.common.model.question.domain.QuestionDomainModel {
        return with(model) {
            com.space.common.model.question.domain.QuestionDomainModel(
                questionTitle = questionTitle,
                answers = answers.map {
                    com.space.common.model.question.domain.QuestionDomainModel.AnswerDomainModel(
                        it,
                        correctAnswer == it
                    )
                }
                    .toMutableList(),
                correctAnswer = com.space.common.model.question.domain.QuestionDomainModel.AnswerDomainModel(
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
