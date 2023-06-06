package com.space.quizapp.domain.usecase.questions.check_answers

import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.domain.repository.quiz.QuizRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import org.koin.java.KoinJavaComponent.inject

abstract class CheckAnswersUseCase :
    QuizBaseUseCase<Nothing, List<QuizQuestionDomainModel.QuizAnswerDomainModel>>() {
    override val repository by inject<QuizRepository>(QuizRepository::class.java)
}
