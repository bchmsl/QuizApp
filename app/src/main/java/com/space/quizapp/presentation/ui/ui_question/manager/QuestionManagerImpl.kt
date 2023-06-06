package com.space.quizapp.presentation.ui.ui_question.manager

import com.space.quizapp.common.extensions.utils.log
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel
import com.space.quizapp.presentation.ui.ui_question.util.QuizAnswerSelectedState

class QuestionManagerImpl : QuestionManager() {
    override var subjects: List<QuizSubjectDomainModel> = emptyList()
    override var category: String = ""
    override var index = -1

    override suspend fun submitQuestionsList(questions: List<QuizSubjectDomainModel>) {
        subjects = questions
    }

    override suspend fun selectCategory(category: String) {
        this.category = category
    }

    override suspend fun getNextQuestion(): QuizQuestionDomainModel? {
        val lastIndex = subject?.questionsCount?.minus(1) ?: -1
        if (index >= lastIndex || lastIndex == -1) return null
        index += 1
        log(questions?.get(index))
        return questions?.get(index)
    }

    override suspend fun getCorrectAnswer(): QuizAnswerDomainModel? {
        return questions?.get(index)?.correctAnswer
    }

    override suspend fun getCheckedAnswersList(
        submittedAnswer: String,
        answers: MutableList<QuizAnswerDomainModel>
    ): MutableList<QuizAnswerDomainModel>? {

        getCorrectAnswer()?.let { correctAnswer ->

            answers[answers.indexOf(correctAnswer)] =
                correctAnswer.copy(selectedState = QuizAnswerSelectedState.ANSWER_SELECTED_CORRECT)

            if (submittedAnswer != correctAnswer.answerOption) {
                val wrongAnswerModel = answers.find { it.answerOption == submittedAnswer }

                answers[answers.indexOf(wrongAnswerModel)] =
                    wrongAnswerModel!!.copy(selectedState = QuizAnswerSelectedState.ANSWER_SELECTED_INCORRECT)
            }

            return answers
        }
        return null
    }
}
