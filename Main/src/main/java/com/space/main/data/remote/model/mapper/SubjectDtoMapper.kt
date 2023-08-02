package com.space.main.data.remote.model.mapper

import com.space.common.model.subject.domain.SubjectDomainModel
import com.space.main.data.remote.model.SubjectDto

class SubjectDtoMapper :
    com.space.common.mapper.DtoMapper<SubjectDto, SubjectDomainModel> {
    override fun toDomain(model: SubjectDto): SubjectDomainModel {
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
}
