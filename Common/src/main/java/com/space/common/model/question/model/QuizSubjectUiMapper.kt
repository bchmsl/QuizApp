package com.space.common.model.question.model

import com.space.common.model.subject.domain.QuizSubjectDomainModel

class QuizSubjectUiMapper :
    com.space.common.mapper.QuizUiMapper<QuizSubjectUiModel, QuizSubjectDomainModel> {
    override fun toDomain(model: QuizSubjectUiModel): QuizSubjectDomainModel {
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

    override fun toUi(model: QuizSubjectDomainModel): QuizSubjectUiModel {
        return with(model) {
            QuizSubjectUiModel(
                id = id,
                quizTitle = quizTitle,
                quizDescription = quizDescription,
                quizIcon = quizIcon,
                questionsCount = questionsCount,
            )
        }
    }
}
