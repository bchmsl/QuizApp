package com.space.quizapp.domain.usecase.user.subject

import com.space.quizapp.domain.model.user.QuizUserSubjectDomainModel
import com.space.quizapp.domain.repository.user.QuizUserSubjectRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase

class QuizReadUserSubjectsUseCase(
    private val repository: QuizUserSubjectRepository
) : QuizBaseUseCase<String, List<QuizUserSubjectDomainModel>>() {
    override suspend fun invoke(params: String?): List<QuizUserSubjectDomainModel> {
        return repository.retrieveUserSubjects(params!!)
    }
}
