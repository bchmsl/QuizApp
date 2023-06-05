package com.space.quizapp.data.remote.model.mapper.subject

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.data.remote.model.QuizSubjectDto
import com.space.quizapp.data.remote.model.mapper.question.QuizQuestionDtoDomainMapper
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel

class QuizSubjectDtoDomainMapper(
    private val quizQuestionDtoDomainMapper: QuizQuestionDtoDomainMapper
) : QuizModelMapper<QuizSubjectDto, QuizSubjectDomainModel> {
    override fun invoke(model: QuizSubjectDto): QuizSubjectDomainModel {
        return QuizSubjectDomainModel(
            id = model.id,
            quizTitle = model.quizTitle,
            quizDescription = model.quizDescription,
            quizIcon = model.quizIcon,
            questionsCount = model.questionsCount,
            questions = model.questions.map { quizQuestionDtoDomainMapper(it) }
        )
    }
}
