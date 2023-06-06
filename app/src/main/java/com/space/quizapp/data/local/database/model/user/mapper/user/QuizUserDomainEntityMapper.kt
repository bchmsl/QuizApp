package com.space.quizapp.data.local.database.model.user.mapper.user

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.data.local.database.model.user.QuizUserEntity
import com.space.quizapp.domain.model.user.QuizUserDomainModel

class QuizUserDomainEntityMapper : QuizModelMapper<QuizUserDomainModel, QuizUserEntity> {
    override fun invoke(model: QuizUserDomainModel): QuizUserEntity {
        return QuizUserEntity(
            username = model.username,
            token = model.token,
            gpa = model.gpa,
        )
    }
}
