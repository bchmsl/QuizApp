package com.space.quizapp.data.remote.model.mapper

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.data.remote.model.QuizQuestionsDto
import com.space.quizapp.domain.model.quiz.QuizQuestionsDomainModel

class QuizQuestionsDtoDomainMapper(
    private val quizQuestionDtoDomainMapper: QuizQuestionDtoDomainMapper
) : QuizModelMapper<QuizQuestionsDto, QuizQuestionsDomainModel> {
    override operator fun invoke(model: QuizQuestionsDto): QuizQuestionsDomainModel =
        QuizQuestionsDomainModel(
            id = model.id,
            quizTitle = model.quizTitle,
            quizDescription = model.quizDescription,
            quizIcon = model.quizIcon,
            questionsCount = model.questionsCount,
            questions = model.questions.map { quizQuestionDtoDomainMapper(it) }
        )
}
