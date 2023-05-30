package com.space.quizapp.presentation.model.mapper

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.domain.model.QuizQuestionsDomainModel
import com.space.quizapp.presentation.model.QuizQuestionsUiModel

class QuizQuestionDomainUiMapper :
    QuizModelMapper<QuizQuestionsDomainModel.Question, QuizQuestionsUiModel.Question> {
    override fun invoke(model: QuizQuestionsDomainModel.Question): QuizQuestionsUiModel.Question =
        QuizQuestionsUiModel.Question(
            questionTitle = model.questionTitle,
            answers = model.answers,
            correctAnswer = model.correctAnswer,
            questionIndex = model.questionIndex
        )
}
