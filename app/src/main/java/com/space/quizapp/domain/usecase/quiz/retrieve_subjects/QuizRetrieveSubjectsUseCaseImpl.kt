package com.space.quizapp.domain.usecase.quiz.retrieve_subjects

import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel

class QuizRetrieveSubjectsUseCaseImpl : QuizRetrieveSubjectsUseCase() {
    override suspend fun invoke(params: Unit?): List<QuizSubjectDomainModel> {
        return repository.retrieveSubjects()
    }
}
