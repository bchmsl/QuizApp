package com.space.quizapp.presentation.model.mapper

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.domain.model.QuizQuestionsDomainModel
import com.space.quizapp.presentation.model.QuizQuestionsUiModel

class QuizQuestionsDomainUiMapper(
    private val quizQuestionDomainUiMapper: QuizQuestionDomainUiMapper
) : QuizModelMapper<QuizQuestionsDomainModel, QuizQuestionsUiModel> {
    override fun invoke(model: QuizQuestionsDomainModel): QuizQuestionsUiModel =
        QuizQuestionsUiModel(
            id = model.id,
            quizTitle = model.quizTitle,
            quizDescription = model.quizDescription,
            quizIcon = model.quizIcon,
            questionsCount = model.questionsCount,
            questions = model.questions.map { quizQuestionDomainUiMapper(it) }

        )
}
