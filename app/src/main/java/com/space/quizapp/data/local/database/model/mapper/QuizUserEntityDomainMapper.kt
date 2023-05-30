package com.space.quizapp.data.local.database.model.mapper

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.data.local.database.model.QuizUserEntity
import com.space.quizapp.domain.model.QuizUserDomainModel

class QuizUserEntityDomainMapper : QuizModelMapper<QuizUserEntity, QuizUserDomainModel> {
    override fun invoke(model: QuizUserEntity): QuizUserDomainModel =
        QuizUserDomainModel(
            username = model.username,
            token = model.token,
            gpa = model.gpa,
        )
}
