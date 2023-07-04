package com.space.quizapp.domain.usecase.quiz

import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel
import com.space.quizapp.domain.repository.quiz.QuizSubjectsRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase

class QuizRetrieveSubjectsUseCase(
    private val subjectsRepository: QuizSubjectsRepository
) : QuizBaseUseCase<Unit, List<QuizSubjectDomainModel>>() {

    override suspend fun invoke(params: Unit?): List<QuizSubjectDomainModel> {
        return subjectsRepository.retrieveSubjects()
    }
}
