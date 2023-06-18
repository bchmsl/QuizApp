package com.space.quizapp.presentation.model.quiz.mapper

import com.space.quizapp.common.mapper.QuizUiMapper
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel
import com.space.quizapp.presentation.model.quiz.QuizSubjectUiModel

class QuizSubjectUiMapper : QuizUiMapper<QuizSubjectUiModel, QuizSubjectDomainModel> {
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