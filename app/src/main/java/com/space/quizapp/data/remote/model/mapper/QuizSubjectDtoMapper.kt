package com.space.quizapp.data.remote.model.mapper

import com.space.common.mapper.QuizDtoMapper
import com.space.common.model.subject.domain.QuizSubjectDomainModel
import com.space.quizapp.data.remote.model.QuizSubjectDto

class QuizSubjectDtoMapper :
    com.space.common.mapper.QuizDtoMapper<QuizSubjectDto, com.space.common.model.subject.domain.QuizSubjectDomainModel> {
    override fun toDomain(model: QuizSubjectDto): com.space.common.model.subject.domain.QuizSubjectDomainModel {
        return with(model) {
            com.space.common.model.subject.domain.QuizSubjectDomainModel(
                id = id,
                quizTitle = quizTitle,
                quizDescription = quizDescription,
                quizIcon = quizIcon,
                questionsCount = questionsCount,

                )
        }
    }
}
