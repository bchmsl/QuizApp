package com.space.quizapp.data.remote.model.mapper

import com.space.quizapp.common.mapper.QuizDtoMapper
import com.space.quizapp.data.remote.model.QuizSubjectDto
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel

class QuizSubjectDtoMapper : QuizDtoMapper<QuizSubjectDto, QuizSubjectDomainModel> {
    override fun toDomain(model: QuizSubjectDto): QuizSubjectDomainModel {
        return with(model) {
            QuizSubjectDomainModel(
                id = id,
                quizTitle = quizTitle,
                quizDescription = quizDescription,
                quizIcon = quizIcon,
                questionsCount = questionsCount,

                )
        }
    }
}
