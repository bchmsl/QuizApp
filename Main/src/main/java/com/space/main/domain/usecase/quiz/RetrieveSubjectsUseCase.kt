package com.space.main.domain.usecase.quiz

import com.space.common.base.usecase.BaseUseCase
import com.space.common.model.subject.domain.SubjectDomainModel
import com.space.main.domain.repository.quiz.SubjectsRepository

class RetrieveSubjectsUseCase(
    private val subjectsRepository: SubjectsRepository
) : BaseUseCase<Unit, List<SubjectDomainModel>>() {

    override suspend fun invoke(params: Unit?): List<SubjectDomainModel> {
        return subjectsRepository.retrieveSubjects()
    }
}
