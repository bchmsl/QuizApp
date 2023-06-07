package com.space.quizapp.domain.usecase.quiz.retrieve_questions

import com.space.quizapp.domain.repository.quiz.QuizRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import com.space.quizapp.presentation.ui.ui_question.manager.QuizManager
import org.koin.java.KoinJavaComponent.inject

abstract class QuizRetrieveQuestionsUseCase :
    QuizBaseUseCase<Int, Unit>() {
    protected val repository by inject<QuizRepository>(QuizRepository::class.java)
    protected val quizManager by inject<QuizManager>(QuizManager::class.java)
}
