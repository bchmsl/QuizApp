package com.space.quizapp.presentation.ui.ui_question.manager

import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel

abstract class QuizManager {
    protected var questions: List<QuizQuestionDomainModel> = emptyList()
    protected var index = -1
    protected val lastIndex get() = questions.lastIndex
    fun submitQuestions(questions: List<QuizQuestionDomainModel>) {
        this.questions = questions
    }

    abstract suspend fun getNextQuestion(): QuizQuestionDomainModel
    abstract suspend fun checkAnswer(
        answer: QuizQuestionDomainModel.QuizAnswerDomainModel
    ): List<QuizQuestionDomainModel.QuizAnswerDomainModel>
}
