package com.space.quizapp.presentation.model.quiz.mapper

import com.space.quizapp.common.mapper.QuizModelMapper
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel
import com.space.quizapp.presentation.model.quiz.QuizQuestionsUiModel

class QuizQuestionDomainUiMapper :
    QuizModelMapper<QuizSubjectDomainModel.QuizQuestionDomainModel, QuizQuestionsUiModel.Question> {
    override fun invoke(model: QuizSubjectDomainModel.QuizQuestionDomainModel): QuizQuestionsUiModel.Question =
        QuizQuestionsUiModel.Question(
            questionTitle = model.questionTitle,
            answers = model.answers,
            correctAnswer = model.correctAnswer,
            questionIndex = model.questionIndex
        )
}
