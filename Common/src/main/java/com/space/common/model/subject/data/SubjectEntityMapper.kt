package com.space.common.model.subject.data

import com.space.common.mapper.EntityMapper
import com.space.common.model.subject.domain.SubjectDomainModel

class SubjectEntityMapper :
    EntityMapper<SubjectEntity, SubjectDomainModel> {
    override fun toDomain(model: SubjectEntity): SubjectDomainModel {
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

    override fun toEntity(model: SubjectDomainModel): SubjectEntity {
        return with(model) {
            SubjectEntity(
                id = id,
                quizTitle = quizTitle,
                quizDescription = quizDescription,
                quizIcon = quizIcon,
                questionsCount = questionsCount,
            )
        }
    }
}
