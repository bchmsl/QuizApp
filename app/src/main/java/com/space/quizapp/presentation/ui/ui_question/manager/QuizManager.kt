package com.space.quizapp.presentation.ui.ui_question.manager

import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel

abstract class QuizManager {
    protected var index = -1
    protected var questions: List<QuizQuestionDomainModel> = emptyList()
    protected val lastIndex get() = questions.lastIndex
    protected var points = 0
    fun submitQuestions(questions: List<QuizQuestionDomainModel>) {
        this.questions = questions
        index = -1
        points = 0
    }

    abstract suspend fun getNextQuestionIfExists(): QuizQuestionDomainModel?
    abstract suspend fun checkAnswer(
        answer: QuizQuestionDomainModel.QuizAnswerDomainModel
    ): List<QuizQuestionDomainModel.QuizAnswerDomainModel>

    fun getUserPoints() = points
    fun isLastQuestion() = index == lastIndex

}
