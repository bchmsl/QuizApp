package com.space.common.model.subject.data

import com.space.common.mapper.QuizEntityMapper
import com.space.common.model.subject.domain.QuizSubjectDomainModel

class QuizSubjectEntityMapper :
    QuizEntityMapper<QuizSubjectEntity, QuizSubjectDomainModel> {
    override fun toDomain(model: QuizSubjectEntity): QuizSubjectDomainModel {
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

    override fun toEntity(model: QuizSubjectDomainModel): QuizSubjectEntity {
        return with(model) {
            QuizSubjectEntity(
                id = id,
                quizTitle = quizTitle,
                quizDescription = quizDescription,
                quizIcon = quizIcon,
                questionsCount = questionsCount,
            )
        }
    }
}
