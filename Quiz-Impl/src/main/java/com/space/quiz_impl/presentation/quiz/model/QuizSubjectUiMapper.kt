package com.space.quiz_impl.presentation.quiz.model

import com.space.quiz_impl.domain.model.QuizSubjectDomainModel

class QuizSubjectUiMapper :
    com.space.common.mapper.QuizUiMapper<QuizSubjectUiModel, QuizSubjectDomainModel> {
    override fun toDomain(model: QuizSubjectUiModel): QuizSubjectDomainModel {
        return with(model) {
            QuizSubjectDomainModel(
                id = id,
                quizTitle = quizTitle,
                quizDescription = quizDescription,
                quizIcon = quizIcon,
                questionsCount = questionsCount,
            )
        }
    }

    override fun toUi(model: QuizSubjectDomainModel): QuizSubjectUiModel {
        return with(model) {
            QuizSubjectUiModel(
                id = id,
                quizTitle = quizTitle,
                quizDescription = quizDescription,
                quizIcon = quizIcon,
                questionsCount = questionsCount,
            )
        }
    }
}
