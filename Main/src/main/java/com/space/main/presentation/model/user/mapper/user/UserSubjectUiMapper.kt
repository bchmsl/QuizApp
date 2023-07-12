package com.space.main.presentation.model.user.mapper.user

import com.space.common.mapper.UiMapper
import com.space.main.domain.model.user.UserSubjectDomainModel
import com.space.main.presentation.model.user.UserSubjectUiModel

class UserSubjectUiMapper :
    UiMapper<UserSubjectUiModel, UserSubjectDomainModel> {
    override fun toDomain(model: UserSubjectUiModel): UserSubjectDomainModel {
        return with(model) {
            UserSubjectDomainModel(
                quizTitle = quizTitle,
                score = score,
                quizDescription = quizDescription,
                quizIcon = quizIcon,
                questionsCount = questionsCount,
                maxScore = maxScore
            )
        }
    }

    override fun toUi(model: UserSubjectDomainModel): UserSubjectUiModel {
        return with(model) {
            UserSubjectUiModel(
                quizTitle = quizTitle,
                score = score,
                quizIcon = quizIcon,
                quizDescription = quizDescription,
                questionsCount = questionsCount,
                maxScore = maxScore
            )
        }
    }
}
