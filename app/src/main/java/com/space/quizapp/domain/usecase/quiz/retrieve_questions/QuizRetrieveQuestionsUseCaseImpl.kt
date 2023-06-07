package com.space.quizapp.domain.usecase.quiz.retrieve_questions

import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.withContext

class QuizRetrieveQuestionsUseCaseImpl : QuizRetrieveQuestionsUseCase() {
    override suspend fun invoke(subjectId: Int?) {
        val questions = repository.getLocalQuestionsBySubject(subjectId!!)
        withContext(Main) {
            quizManager.submitQuestions(questions)
        }
    }
}
