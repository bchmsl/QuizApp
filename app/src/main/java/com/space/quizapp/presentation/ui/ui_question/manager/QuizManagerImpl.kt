package com.space.quizapp.presentation.ui.ui_question.manager

import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.presentation.ui.ui_question.util.QuizAnswerSelectedState


class QuizManagerImpl : QuizManager() {

    override suspend fun getNextQuestion(): QuizQuestionDomainModel {
        if (index < lastIndex) {
            index += 1
        }
        return questions[index]
    }

    override suspend fun checkAnswer(answer: QuizQuestionDomainModel.QuizAnswerDomainModel)
            : List<QuizQuestionDomainModel.QuizAnswerDomainModel> {
        val currentQuestion = questions[index]
        val correctAnswer = currentQuestion.correctAnswer
        val isCorrect = answer.answerOption == correctAnswer.answerOption
        val correctInAnswersIndex = currentQuestion.answers.indexOf(correctAnswer)
        val currentInAnswersIndex = currentQuestion.answers.indexOf(answer)
        if (isCorrect) {
            currentQuestion.answers[correctInAnswersIndex] = correctAnswer
        } else {
            currentQuestion.answers[currentInAnswersIndex] =
                answer.copy(selectedState = QuizAnswerSelectedState.ANSWER_SELECTED_INCORRECT)
            currentQuestion.answers[correctInAnswersIndex] = correctAnswer
        }
        return currentQuestion.answers
    }
}
