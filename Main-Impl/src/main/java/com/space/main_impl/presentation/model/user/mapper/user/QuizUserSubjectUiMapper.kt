package com.space.main_impl.presentation.model.user.mapper.user

import com.space.common.mapper.QuizUiMapper
import com.space.main_impl.domain.model.user.QuizUserSubjectDomainModel
import com.space.main_impl.presentation.model.user.QuizUserSubjectUiModel

class QuizUserSubjectUiMapper :
    QuizUiMapper<QuizUserSubjectUiModel, QuizUserSubjectDomainModel> {
    override fun toDomain(model: QuizUserSubjectUiModel): QuizUserSubjectDomainModel {
        return with(model) {
            QuizUserSubjectDomainModel(
                quizTitle = quizTitle,
                score = score,
                quizDescription = quizDescription,
                quizIcon = quizIcon,
                questionsCount = questionsCount,
                maxScore = maxScore
            )
        }
    }

    override fun toUi(model: QuizUserSubjectDomainModel): QuizUserSubjectUiModel {
        return with(model) {
            QuizUserSubjectUiModel(
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
