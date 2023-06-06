package com.space.quizapp.data.local.database.model.quiz.mapper.question

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.data.local.database.model.quiz.questions.QuizQuestionEntity
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel

class QuizQuestionDomainEntityMapper :
    QuizModelMapper<QuizQuestionDomainModel, QuizQuestionEntity> {
    override fun invoke(model: QuizQuestionDomainModel): QuizQuestionEntity {
        return QuizQuestionEntity(
            questionTitle = model.questionTitle,
            answers = model.answers.map { it.answerOption },
            correctAnswer = model.correctAnswer.answerOption,
            subjectId = model.subjectId,
            questionIndex = model.questionIndex
        )
    }
}
