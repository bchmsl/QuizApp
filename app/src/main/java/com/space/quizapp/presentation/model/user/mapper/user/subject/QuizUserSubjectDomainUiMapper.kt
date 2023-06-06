package com.space.quizapp.presentation.model.user.mapper.user.subject

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.domain.model.user.QuizUserSubjectDomainModel
import com.space.quizapp.presentation.model.user.QuizUserSubjectUiModel

class QuizUserSubjectDomainUiMapper :
    QuizModelMapper<QuizUserSubjectDomainModel, QuizUserSubjectUiModel> {
    override fun invoke(model: QuizUserSubjectDomainModel): QuizUserSubjectUiModel {
        return QuizUserSubjectUiModel(
            username = model.username,
            subjectId = model.subjectId,
            score = model.score
        )
    }
}
