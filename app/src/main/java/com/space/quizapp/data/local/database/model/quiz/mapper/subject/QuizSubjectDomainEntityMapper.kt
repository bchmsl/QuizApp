package com.space.quizapp.data.local.database.model.quiz.mapper.subject

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.data.local.database.model.quiz.mapper.question.QuizQuestionDomainEntityMapper
import com.space.quizapp.data.local.database.model.quiz.subject.QuizSubjectEntity
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel

class QuizSubjectDomainEntityMapper(
    private val quizQuestionDomainEntityMapper: QuizQuestionDomainEntityMapper
) : QuizModelMapper<QuizSubjectDomainModel, QuizSubjectEntity> {
    override fun invoke(model: QuizSubjectDomainModel): QuizSubjectEntity {
        return QuizSubjectEntity(
            id = model.id,
            quizTitle = model.quizTitle,
            quizDescription = model.quizDescription,
            quizIcon = model.quizIcon,
            questionsCount = model.questionsCount,
        )
    }
}
