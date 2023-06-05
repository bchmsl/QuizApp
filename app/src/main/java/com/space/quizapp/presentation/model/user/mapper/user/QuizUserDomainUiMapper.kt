package com.space.quizapp.presentation.model.user.mapper.user

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.presentation.model.user.QuizUserUiModel
import com.space.quizapp.presentation.model.user.mapper.subject.QuizUserSubjectDomainUiMapper

class QuizUserDomainUiMapper(
    private val quizUserSubjectDomainUiMapper: QuizUserSubjectDomainUiMapper
) : QuizModelMapper<QuizUserDomainModel, QuizUserUiModel> {
    override fun invoke(model: QuizUserDomainModel): QuizUserUiModel {
        return QuizUserUiModel(
            userName = model.username,
            gpa = model.gpa,
            subjects = model.subjects.map { quizUserSubjectDomainUiMapper(it) }
        )
    }
}
