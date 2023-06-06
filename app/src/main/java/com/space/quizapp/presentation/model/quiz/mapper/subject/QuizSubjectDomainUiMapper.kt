package com.space.quizapp.presentation.model.quiz.mapper.subject

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel
import com.space.quizapp.presentation.model.quiz.QuizSubjectUiModel

class QuizSubjectDomainUiMapper : QuizModelMapper<QuizSubjectDomainModel, QuizSubjectUiModel> {
    override fun invoke(model: QuizSubjectDomainModel): QuizSubjectUiModel {
        return QuizSubjectUiModel(
            id = model.id,
            quizTitle = model.quizTitle,
            quizDescription = model.quizDescription,
            quizIcon = model.quizIcon,
            questionsCount = model.questionsCount,
        )
    }
}
