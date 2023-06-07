package com.space.quizapp.domain.usecase.quiz.retrieve_subjects

import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel
import com.space.quizapp.domain.repository.quiz.QuizRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import org.koin.java.KoinJavaComponent.inject

abstract class QuizRetrieveSubjectsUseCase :
    QuizBaseUseCase<Unit, List<QuizSubjectDomainModel>>() {
    protected val repository by inject<QuizRepository>(QuizRepository::class.java)
}
