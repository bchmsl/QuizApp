package com.space.quizapp.data.local.database.model.quiz.mapper.subject

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.data.local.database.model.quiz.QuizSubjectEntity
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel

class QuizSubjectDomainEntityMapper : QuizModelMapper<QuizSubjectDomainModel, QuizSubjectEntity> {
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
