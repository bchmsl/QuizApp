package com.space.quizapp.presentation.ui.ui_question.manager

import com.space.quizapp.common.extensions.utils.log
import com.space.quizapp.presentation.model.quiz.QuizQuestionsUiModel
import com.space.quizapp.presentation.ui.ui_question.model.AnswerModel
import com.space.quizapp.presentation.ui.ui_question.util.QuizAnswerSelectedState

class QuestionManagerImpl : QuestionManager() {
    override var subjects: List<QuizQuestionsUiModel> = emptyList()
    override var category: String = ""
    override var index = -1

    override suspend fun submitQuestionsList(questions: List<QuizQuestionsUiModel>) {
        subjects = questions
    }

    override suspend fun selectCategory(category: String) {
        this.category = category
    }

    override suspend fun getNextQuestion(): QuizQuestionsUiModel.Question? {
        val lastIndex = subject?.questionsCount?.minus(1) ?: -1
        if (index >= lastIndex || lastIndex == -1) return null
        index += 1
        log(questions?.get(index))
        return questions?.get(index)
    }

    override suspend fun getCorrectAnswer(): String? {
        return questions?.get(index)?.correctAnswer
    }

    override suspend fun getCheckedAnswersList(
        submittedAnswer: String,
        answers: MutableList<AnswerModel>
    ): MutableList<AnswerModel>? {
        getCorrectAnswer()?.let { correctAnswer ->
            val correctAnswerModel = answers.find { it.answerOption == correctAnswer }
            answers[answers.indexOf(correctAnswerModel)] =
                correctAnswerModel!!.copy(selectedState = QuizAnswerSelectedState.ANSWER_SELECTED_CORRECT)
            if (submittedAnswer != correctAnswer) {
                val wrongAnswerModel = answers.find { it.answerOption == submittedAnswer }
                answers[answers.indexOf(wrongAnswerModel)] =
                    wrongAnswerModel!!.copy(selectedState = QuizAnswerSelectedState.ANSWER_SELECTED_INCORRECT)
            }
            return answers
        }
        return null
    }
}
