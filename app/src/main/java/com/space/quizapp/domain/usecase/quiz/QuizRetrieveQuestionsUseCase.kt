package com.space.quizapp.domain.usecase.quiz

import com.space.quizapp.domain.repository.quiz.QuizRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import com.space.quizapp.presentation.ui.ui_question.manager.QuizManager
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.withContext

class QuizRetrieveQuestionsUseCase(
    private val repository: QuizRepository,
    private val manager: QuizManager
) : QuizBaseUseCase<Int, Unit>() {

    override suspend fun invoke(subjectId: Int?) {
        val questions = repository.getLocalQuestionsBySubjectId(subjectId!!)
        withContext(Main) {
            manager.submitQuestions(questions)
        }
    }
}
