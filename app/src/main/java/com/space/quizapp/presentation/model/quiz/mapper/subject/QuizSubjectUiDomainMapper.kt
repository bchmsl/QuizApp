package com.space.quizapp.presentation.model.quiz.mapper.subject

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel
import com.space.quizapp.presentation.model.quiz.QuizSubjectUiModel
import com.space.quizapp.presentation.model.quiz.mapper.question.QuizQuestionUiDomainMapper

class QuizSubjectUiDomainMapper(
    private val quizQuestionUiDomainMapper: QuizQuestionUiDomainMapper
) : QuizModelMapper<QuizSubjectUiModel, QuizSubjectDomainModel> {
    override fun invoke(model: QuizSubjectUiModel): QuizSubjectDomainModel {
        return QuizSubjectDomainModel(
            id = model.id,
            quizTitle = model.quizTitle,
            quizDescription = model.quizDescription,
            quizIcon = model.quizIcon,
            questionsCount = model.questionsCount,
            questions = model.questions.map { quizQuestionUiDomainMapper(it) }
        )
    }
}
