package com.space.quizapp.presentation.common.model.mapper

import com.space.quizapp.common.mapper.ModelMapper
import com.space.quizapp.domain.model.SubjectDomainModel
import com.space.quizapp.presentation.common.model.SubjectUiModel

class SubjectUiDomainMapper : ModelMapper<SubjectUiModel, SubjectDomainModel> {
    override fun invoke(model: SubjectUiModel): SubjectDomainModel =
        SubjectDomainModel(
            id = model.id,
            username = model.userName,
            title = model.title,
            description = model.description,
            icon = model.icon,
            score = model.score
        )
}
