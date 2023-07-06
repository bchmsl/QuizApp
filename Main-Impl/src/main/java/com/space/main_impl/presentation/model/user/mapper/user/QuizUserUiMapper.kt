package com.space.main_impl.presentation.model.user.mapper.user

import com.space.common.mapper.QuizUiMapper
import com.space.main_impl.domain.model.user.QuizUserDomainModel
import com.space.main_impl.presentation.model.user.QuizUserUiModel

class QuizUserUiMapper :
    QuizUiMapper<QuizUserUiModel, QuizUserDomainModel> {
    override fun toDomain(model: QuizUserUiModel): QuizUserDomainModel {
        return with(model) {
            QuizUserDomainModel(
                username = userName,
                gpa = gpa
            )
        }
    }

    override fun toUi(model: QuizUserDomainModel): QuizUserUiModel {
        return with(model) {
            QuizUserUiModel(
                userName = username,
                gpa = gpa
            )
        }
    }
}
