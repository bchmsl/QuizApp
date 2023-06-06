package com.space.quizapp.data.local.database.model.quiz.mapper.question

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.data.local.database.model.quiz.QuizQuestionEntity
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel

class QuizQuestionEntityDomainMapper :
    QuizModelMapper<QuizQuestionEntity, QuizQuestionDomainModel> {
    override fun invoke(model: QuizQuestionEntity): QuizQuestionDomainModel {
        return QuizQuestionDomainModel(
            questionTitle = model.questionTitle,
            answers = model.answers.map { QuizQuestionDomainModel.QuizAnswerDomainModel(it) },
            correctAnswer = QuizQuestionDomainModel.QuizAnswerDomainModel(model.correctAnswer),
            subjectId = model.subjectId,
            questionIndex = model.questionIndex
        )
    }
}
