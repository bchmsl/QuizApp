package com.space.quizapp.data.local.database.model.mapper

import com.space.quizapp.common.mapper.ModelMapper
import com.space.quizapp.data.local.database.model.SubjectEntity
import com.space.quizapp.domain.model.SubjectDomainModel

class SubjectEntityDomainMapper :
    ModelMapper<SubjectEntity, SubjectDomainModel> {
    override fun invoke(model: SubjectEntity): SubjectDomainModel =
        SubjectDomainModel(
            id = model.id,
            username = model.username,
            title = model.title,
            description = model.description,
            icon = model.icon,
            score = model.score,
        )
}
