package com.space.quizapp.data.local.database.model.quiz.mapper

import com.space.common.mapper.QuizEntityMapper
import com.space.quizapp.data.local.database.model.quiz.QuizSubjectEntity
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel

class QuizSubjectEntityMapper :
    com.space.common.mapper.QuizEntityMapper<QuizSubjectEntity, QuizSubjectDomainModel> {
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
