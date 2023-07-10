package com.space.main.data.remote.model.mapper

import com.space.common.model.subject.domain.QuizSubjectDomainModel
import com.space.main.data.remote.model.QuizSubjectDto

class QuizSubjectDtoMapper :
    com.space.common.mapper.QuizDtoMapper<QuizSubjectDto, QuizSubjectDomainModel> {
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
