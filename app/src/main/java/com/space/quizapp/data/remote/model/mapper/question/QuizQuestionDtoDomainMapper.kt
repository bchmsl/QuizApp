package com.space.quizapp.data.remote.model.mapper.question

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.data.remote.model.QuizSubjectDto
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel

class QuizQuestionDtoDomainMapper :
    QuizModelMapper<QuizSubjectDto.QuizQuestionDto, QuizQuestionDomainModel> {
    override fun invoke(model: QuizSubjectDto.QuizQuestionDto): QuizQuestionDomainModel {
        return QuizQuestionDomainModel(
            questionTitle = model.questionTitle,
            answers = model.answers.map { QuizQuestionDomainModel.QuizAnswerDomainModel(it) }
                .toMutableList(),
            correctAnswer = QuizQuestionDomainModel.QuizAnswerDomainModel(model.correctAnswer),
            subjectId = model.subjectId,
            questionIndex = model.questionIndex
        )
    }
}
