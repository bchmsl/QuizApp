package com.space.quizapp.presentation.ui.ui_question.manager

import com.space.quizapp.presentation.model.quiz.QuizQuestionsUiModel
import com.space.quizapp.presentation.ui.ui_question.model.AnswerModel

abstract class QuestionManager {
    abstract var subjects: List<QuizQuestionsUiModel>
    protected val subject get() = subjects.find { subject -> subject.quizTitle == category }
    protected val questions get() = subject?.questions
    abstract var category: String
    abstract var index: Int

    abstract suspend fun submitQuestionsList(questions: List<QuizQuestionsUiModel>)
    abstract suspend fun selectCategory(category: String)
    abstract suspend fun getNextQuestion(): QuizQuestionsUiModel.Question?
    abstract suspend fun getCorrectAnswer(): String?
    abstract suspend fun getCheckedAnswersList(
        submittedAnswer: String,
        answers: MutableList<AnswerModel>
    ): MutableList<AnswerModel>?
}
