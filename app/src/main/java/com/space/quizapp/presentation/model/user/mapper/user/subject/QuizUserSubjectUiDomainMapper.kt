package com.space.quizapp.presentation.model.user.mapper.user.subject

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.domain.model.user.QuizUserSubjectDomainModel
import com.space.quizapp.presentation.model.user.QuizUserSubjectUiModel

class QuizUserSubjectUiDomainMapper :
    QuizModelMapper<QuizUserSubjectUiModel, QuizUserSubjectDomainModel> {
    override fun invoke(model: QuizUserSubjectUiModel): QuizUserSubjectDomainModel {
        return QuizUserSubjectDomainModel(
            username = model.username,
            subjectId = model.subjectId,
            score = model.score
        )
    }
}
