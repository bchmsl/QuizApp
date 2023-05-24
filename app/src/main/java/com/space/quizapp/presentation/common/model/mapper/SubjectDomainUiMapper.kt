package com.space.quizapp.presentation.common.model.mapper

import com.space.quizapp.common.mapper.ModelMapper
import com.space.quizapp.domain.model.SubjectDomainModel
import com.space.quizapp.presentation.common.model.SubjectUiModel

class SubjectDomainUiMapper : ModelMapper<SubjectDomainModel, SubjectUiModel> {
    override fun invoke(model: SubjectDomainModel): SubjectUiModel =
        SubjectUiModel(
            id = model.id,
            userName = model.username,
            title = model.title,
            description = model.description,
            icon = model.icon,
            score = model.score
        )
}
