package com.space.quizapp.domain.usecase.questions.get_questions

import com.space.quizapp.common.resource.QuizResource
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel
import com.space.quizapp.domain.repository.quiz.QuizRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import org.koin.java.KoinJavaComponent.inject

abstract class QuizQuestionsUseCase :
    QuizBaseUseCase<Nothing, QuizResource<List<QuizSubjectDomainModel>>>() {
    override val repository by inject<QuizRepository>(QuizRepository::class.java)
}
