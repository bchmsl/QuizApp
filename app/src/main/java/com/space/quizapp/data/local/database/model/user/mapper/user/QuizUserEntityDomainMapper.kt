package com.space.quizapp.data.local.database.model.user.mapper.user

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.data.local.database.model.user.QuizUserEntity
import com.space.quizapp.domain.model.user.QuizUserDomainModel

class QuizUserEntityDomainMapper : QuizModelMapper<QuizUserEntity, QuizUserDomainModel> {
    override fun invoke(model: QuizUserEntity): QuizUserDomainModel {
        return QuizUserDomainModel(
            username = model.username,
            token = model.token,
            gpa = model.gpa,
        )
    }
}
