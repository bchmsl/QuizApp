package com.space.quizapp.domain.usecase.questions.next_question

import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import com.space.quizapp.presentation.ui.ui_question.manager.QuizManager
import org.koin.java.KoinJavaComponent.get

abstract class GetNextQuestionUseCase : QuizBaseUseCase<Unit, QuizQuestionDomainModel>() {
    protected val quizManager = get<QuizManager>(QuizManager::class.java)
//    protected val questionManager by inject<QuizManager>(QuizManager::class.java)
}
