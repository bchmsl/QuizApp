package com.space.common.model.question.model

import com.space.common.model.subject.domain.SubjectDomainModel

class SubjectUiMapper :
    com.space.common.mapper.UiMapper<SubjectUiModel, SubjectDomainModel> {
    override fun toDomain(model: SubjectUiModel): SubjectDomainModel {
        return with(model) {
            SubjectDomainModel(
                id = id,
                quizTitle = quizTitle,
                quizDescription = quizDescription,
                quizIcon = quizIcon,
                questionsCount = questionsCount,
            )
        }
    }

    override fun toUi(model: SubjectDomainModel): SubjectUiModel {
        return with(model) {
            SubjectUiModel(
                id = id,
                quizTitle = quizTitle,
                quizDescription = quizDescription,
                quizIcon = quizIcon,
                questionsCount = questionsCount,
            )
        }
    }
}
