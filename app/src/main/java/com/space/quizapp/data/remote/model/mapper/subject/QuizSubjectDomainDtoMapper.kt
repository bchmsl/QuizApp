package com.space.quizapp.data.remote.model.mapper.subject

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.data.remote.model.QuizSubjectDto
import com.space.quizapp.data.remote.model.mapper.question.QuizQuestionDomainDtoMapper
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel

class QuizSubjectDomainDtoMapper(
    private val quizQuestionDomainDtoMapper: QuizQuestionDomainDtoMapper
) : QuizModelMapper<QuizSubjectDomainModel, QuizSubjectDto> {
    override fun invoke(model: QuizSubjectDomainModel): QuizSubjectDto {
        return QuizSubjectDto(
            id = model.id,
            quizTitle = model.quizTitle,
            quizDescription = model.quizDescription,
            quizIcon = model.quizIcon,
            questionsCount = model.questionsCount,
            questions = model.questions.map { quizQuestionDomainDtoMapper(it) }
        )
    }
}
