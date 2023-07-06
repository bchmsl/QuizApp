package com.space.quiz_impl.data.model

import com.space.common.mapper.QuizEntityMapper
import com.space.common.model.subject.data.QuizSubjectEntity
import com.space.quiz_impl.domain.model.QuizSubjectDomainModel

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
