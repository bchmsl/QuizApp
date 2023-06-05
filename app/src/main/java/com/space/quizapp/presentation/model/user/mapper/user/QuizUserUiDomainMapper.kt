package com.space.quizapp.presentation.model.user.mapper.user

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.presentation.model.user.QuizUserUiModel
import com.space.quizapp.presentation.model.user.mapper.subject.QuizUserSubjectUiDomainMapper

class QuizUserUiDomainMapper(
    private val quizUserSubjectUiDomainMapper: QuizUserSubjectUiDomainMapper
) : QuizModelMapper<QuizUserUiModel, QuizUserDomainModel> {
    override fun invoke(model: QuizUserUiModel): QuizUserDomainModel {
        return QuizUserDomainModel(
            username = model.userName,
            gpa = model.gpa,
            subjects = model.subjects.map { quizUserSubjectUiDomainMapper(it) }
        )
    }
}
