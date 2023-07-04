package com.space.quizapp.domain.usecase.quiz

import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel
import com.space.quizapp.domain.repository.quiz.QuizRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase

class QuizRetrieveSubjectsUseCase(
    private val repository: QuizRepository
) : QuizBaseUseCase<Unit, List<QuizSubjectDomainModel>>() {

    override suspend fun invoke(params: Unit?): List<QuizSubjectDomainModel> {
        return repository.retrieveSubjects()
    }
}
