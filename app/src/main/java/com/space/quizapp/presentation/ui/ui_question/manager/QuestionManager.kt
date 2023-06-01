package com.space.quizapp.presentation.ui.ui_question.manager

import com.space.quizapp.presentation.model.quiz.QuizQuestionsUiModel

abstract class QuestionManager {
    abstract var subjects: List<QuizQuestionsUiModel>
    protected val subject get() = subjects.find { subject -> subject.quizTitle == category }
    protected val questions get() = subject?.questions
    abstract var category: String
    abstract var index: Int

    abstract fun submitQuestionsList(questions: List<QuizQuestionsUiModel>)
    abstract fun selectCategory(category: String)
    abstract fun getNextQuestion(): QuizQuestionsUiModel.Question?
    abstract fun getCorrectAnswer(): String?
}
