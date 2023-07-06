package com.space.quizapp.domain.usecase.quiz

import com.space.common.base.usecase.QuizBaseUseCase
import com.space.common.model.subject.domain.QuizSubjectDomainModel
import com.space.quizapp.domain.repository.quiz.QuizSubjectsRepository

class QuizRetrieveSubjectsUseCase(
    private val subjectsRepository: QuizSubjectsRepository
) : com.space.common.base.usecase.QuizBaseUseCase<Unit, List<com.space.common.model.subject.domain.QuizSubjectDomainModel>>() {

    override suspend fun invoke(params: Unit?): List<com.space.common.model.subject.domain.QuizSubjectDomainModel> {
        return subjectsRepository.retrieveSubjects()
    }
}
