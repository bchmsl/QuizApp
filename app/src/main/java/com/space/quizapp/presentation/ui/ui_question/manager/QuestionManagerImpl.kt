package com.space.quizapp.presentation.ui.ui_question.manager

import com.space.quizapp.presentation.model.quiz.QuizQuestionsUiModel

class QuestionManagerImpl : QuestionManager() {
    override var subjects: List<QuizQuestionsUiModel> = emptyList()
    override var category: String = ""
    override var index = -1

    override fun submitQuestionsList(questions: List<QuizQuestionsUiModel>) {
        subjects = questions
    }

    override fun selectCategory(category: String) {
        this.category = category
    }

    override fun getNextQuestion(): QuizQuestionsUiModel.Question? {
        val lastIndex = subject?.questionsCount?.minus(1) ?: -1
        if (index >= lastIndex || lastIndex == -1) return null
        index += 1
        return questions?.get(index)
    }

    override fun getCorrectAnswer(): String? {
        return questions?.get(index)?.correctAnswer
    }
}
