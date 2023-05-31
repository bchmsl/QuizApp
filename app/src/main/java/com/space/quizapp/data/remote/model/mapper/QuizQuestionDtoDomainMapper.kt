package com.space.quizapp.data.remote.model.mapper

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.data.remote.model.QuizQuestionsDto
import com.space.quizapp.domain.model.quiz.QuizQuestionsDomainModel

class QuizQuestionDtoDomainMapper :
    QuizModelMapper<QuizQuestionsDto.Question, QuizQuestionsDomainModel.Question> {

    override operator fun invoke(model: QuizQuestionsDto.Question): QuizQuestionsDomainModel.Question =
        QuizQuestionsDomainModel.Question(
            questionTitle = model.questionTitle,
            answers = model.answers,
            correctAnswer = model.correctAnswer,
            questionIndex = model.questionIndex
        )
}
