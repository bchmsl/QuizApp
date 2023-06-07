package com.space.quizapp.domain.usecase.questions.check_answers

import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.domain.repository.quiz.QuizRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import com.space.quizapp.presentation.ui.ui_question.manager.QuizManager
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.inject

abstract class CheckAnswersUseCase :
    QuizBaseUseCase<QuizQuestionDomainModel.QuizAnswerDomainModel, List<QuizQuestionDomainModel.QuizAnswerDomainModel>>() {
    protected val repository by inject<QuizRepository>(QuizRepository::class.java)
    protected val quizManager = KoinJavaComponent.get<QuizManager>(QuizManager::class.java)

//    protected val quizManager by inject<QuizManager>(QuizManager::class.java)
}
