package com.space.quizapp.presentation.model.user.mapper.user

import com.space.quizapp.common.mapper.QuizUiMapper
import com.space.quizapp.domain.model.user.QuizUserSubjectDomainModel
import com.space.quizapp.presentation.model.user.QuizUserSubjectUiModel

class QuizUserSubjectUiMapper : QuizUiMapper<QuizUserSubjectUiModel, QuizUserSubjectDomainModel> {
    override fun toDomain(model: QuizUserSubjectUiModel): QuizUserSubjectDomainModel {
        return with(model) {
            QuizUserSubjectDomainModel(
                username = username,
                subjectId = subjectId,
                score = score
            )
        }
    }

    override fun toUi(model: QuizUserSubjectDomainModel): QuizUserSubjectUiModel {
        return with(model) {
            QuizUserSubjectUiModel(
                username = username,
                subjectId = subjectId,
                score = score
            )
        }
    }
}
