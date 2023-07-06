package com.space.main_impl.domain.usecase.quiz

import com.space.common.base.usecase.QuizBaseUseCase
import com.space.common.model.subject.domain.QuizSubjectDomainModel
import com.space.main_impl.domain.repository.quiz.QuizSubjectsRepository

class QuizRetrieveSubjectsUseCase(
    private val subjectsRepository: QuizSubjectsRepository
) : QuizBaseUseCase<Unit, List<QuizSubjectDomainModel>>() {

    override suspend fun invoke(params: Unit?): List<QuizSubjectDomainModel> {
        return subjectsRepository.retrieveSubjects()
    }
}