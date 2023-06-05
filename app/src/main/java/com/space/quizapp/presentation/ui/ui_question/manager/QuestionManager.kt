package com.space.quizapp.presentation.ui.ui_question.manager

import com.space.quizapp.domain.model.quiz.QuizAnswerDomainModel
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel

abstract class QuestionManager {
    abstract var subjects: List<QuizSubjectDomainModel>
    protected val subject get() = subjects.find { subject -> subject.quizTitle == category }
    protected val questions get() = subject?.questions
    abstract var category: String
    abstract var index: Int

    abstract suspend fun submitQuestionsList(questions: List<QuizSubjectDomainModel>)
    abstract suspend fun selectCategory(category: String)
    abstract suspend fun getNextQuestion(): QuizQuestionDomainModel?
    abstract suspend fun getCorrectAnswer(): QuizAnswerDomainModel?
    abstract suspend fun getCheckedAnswersList(
        submittedAnswer: String,
        answers: MutableList<QuizAnswerDomainModel>
    ): MutableList<QuizAnswerDomainModel>?
}
